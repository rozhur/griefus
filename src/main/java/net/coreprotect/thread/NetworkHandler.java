package net.coreprotect.thread;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import net.coreprotect.CoreProtect;
import net.coreprotect.config.Config;
import net.coreprotect.config.ConfigFile;
import net.coreprotect.config.ConfigHandler;
import net.coreprotect.language.Language;
import net.coreprotect.language.Phrase;
import net.coreprotect.utility.Chat;
import net.coreprotect.utility.VersionUtils;

public class NetworkHandler extends Language implements Runnable {

    private boolean startup = true;
    private boolean background = false;
    private boolean translate = true;

    public NetworkHandler(boolean startup, boolean background) {
        this.startup = startup;
        this.background = background;
    }

    @Override
    public void run() {
        try {
            if (translate) {
                try {
                    String lang = Config.getGlobal().LANGUAGE;
                    String languageCode = lang.trim().toLowerCase();
                    String pluginVersion = VersionUtils.getPluginVersion();

                    if (!languageCode.startsWith("en") && languageCode.length() > 1) {
                        boolean validCache = false;
                        Path languagePath = Paths.get(ConfigHandler.path + ConfigFile.LANGUAGE);
                        Path languageCachePath = Paths.get(ConfigHandler.path + ConfigFile.LANGUAGE_CACHE);

                        // validate that a valid cache file exists
                        if (Files.isReadable(languagePath) && Files.isReadable(languageCachePath)) {
                            try (Stream<String> stream = Files.lines(languageCachePath)) {
                                Optional<String> languageHeader = stream.findFirst();
                                if (languageHeader.isPresent()) {
                                    String headerString = languageHeader.get();
                                    if (headerString.startsWith("# Griefus")) { // verify that valid cache file
                                        String[] split = headerString.split(" ");
                                        if (split.length == 6 && split[2].length() > 2 && split[5].length() > 2) {
                                            String cacheVersion = split[2].substring(1);
                                            String cacheLanguage = split[5].substring(1, split[5].length() - 1);
                                            if (cacheVersion.equals(pluginVersion) && cacheLanguage.equals(languageCode)) {
                                                validCache = true;
                                            }
                                            else {
                                                ConfigFile.resetCache(ConfigFile.LANGUAGE_CACHE, ConfigFile.LANGUAGE);
                                            }
                                            if (validCache && Files.getLastModifiedTime(languagePath).toMillis() >= Files.getLastModifiedTime(languageCachePath).toMillis()) {
                                                validCache = false;
                                            }
                                        }
                                    }
                                }
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        if (!validCache) {
                            Set<String> phraseSet = new HashSet<>();
                            Map<String, String> phrases = new HashMap<>();

                            for (Phrase phrase : Phrase.values()) {
                                phraseSet.add(phrase.name());
                                phrases.put(phrase.name(), phrase.getUserPhrase());
                            }

                            phrases.put("DATA_VERSION", pluginVersion);
                            phrases.put("DATA_LANGUAGE", languageCode);

                            String mapString = "data=" + JSONObject.toJSONString(phrases);
                            mapString = mapString.replaceAll("\\+", "{PLUS_SIGN}");
                            byte[] postData = mapString.getBytes(StandardCharsets.UTF_8);
                            int postDataLength = postData.length;

                            try {
                                URL url = new URL("http://griefus.zhdev.org/translate/");
                                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                connection.setRequestMethod("POST");
                                connection.setRequestProperty("Accept-Charset", "UTF-8");
                                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
                                connection.setRequestProperty("User-Agent", "Griefus");
                                connection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
                                connection.setDoOutput(true);
                                connection.setInstanceFollowRedirects(true);
                                connection.setUseCaches(false);
                                connection.setConnectTimeout(5000);

                                DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                                outputStream.write(postData);
                                outputStream.close();

                                int status = connection.getResponseCode();
                                if (status == 200) {
                                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
                                    StringBuilder responseBuilder = new StringBuilder();
                                    String responseLine = null;
                                    while ((responseLine = reader.readLine()) != null) {
                                        responseBuilder.append(responseLine.trim());
                                    }
                                    reader.close();

                                    String response = responseBuilder.toString();
                                    if (response.length() > 0 && response.startsWith("{") && response.endsWith("}")) {
                                        TreeMap<Phrase, String> translatedPhrases = new TreeMap<>();
                                        JSONParser parser = new JSONParser();
                                        JSONObject json = (JSONObject) parser.parse(response);
                                        for (Object jsonKey : json.keySet()) {
                                            String key = (String) jsonKey;
                                            String value = ((String) json.get(jsonKey)).trim();
                                            if (phraseSet.contains(key) && value.length() > 0) {
                                                Phrase phrase = Phrase.valueOf(key);
                                                translatedPhrases.put(phrase, value);
                                                Language.setTranslatedPhrase(phrase, value);
                                            }
                                        }

                                        File file = new File(CoreProtect.getInstance().getDataFolder(), ConfigFile.LANGUAGE_CACHE);
                                        try (final FileOutputStream fout = new FileOutputStream(file, false)) {
                                            OutputStreamWriter out = new OutputStreamWriter(new BufferedOutputStream(fout), StandardCharsets.UTF_8);
                                            out.append("# Griefus v" + pluginVersion + " Language Cache (" + languageCode + ")");
                                            out.append(Config.LINE_SEPARATOR);

                                            for (final Entry<Phrase, String> entry : translatedPhrases.entrySet()) {
                                                String key = entry.getKey().name();
                                                String value = entry.getValue().replaceAll("\"", "\\\\\"");

                                                out.append(Config.LINE_SEPARATOR);
                                                out.append(key);
                                                out.append(": ");
                                                out.append("\"" + value + "\"");
                                            }

                                            out.close();
                                        }
                                    }
                                }

                                connection.disconnect();
                            }
                            catch (Exception e) {
                                // Unable to connect to griefus.zhdev.org
                            }
                        }
                    }
                    else {
                        ConfigFile.resetCache(ConfigFile.LANGUAGE_CACHE, ConfigFile.LANGUAGE);
                    }

                    // optionally clear user phrases here
                    translate = false;
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (!Config.getGlobal().CHECK_UPDATES) {
                return;
            }

            if (startup) {
                Thread.sleep(1000);
            }
        }
        catch (Exception e) {
            Chat.console(Phrase.build(Phrase.UPDATE_ERROR));
            e.printStackTrace();
        }
    }
}
