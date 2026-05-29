package net.coreprotect.utility;

import java.util.logging.Level;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.ParsingException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public final class Chat {

    public static final String COMPONENT_TAG_OPEN = "<COMPONENT>";
    public static final String COMPONENT_TAG_CLOSE = "</COMPONENT>";
    public static final String COMPONENT_COMMAND = "COMMAND";
    public static final String COMPONENT_POPUP = "POPUP";
    public static final String COMPONENT_PIPE = "<PIPE/>";

    private Chat() {
        throw new IllegalStateException("Utility class");
    }

    public static void sendComponent(CommandSender sender, String string, String bypass) {
        //SpigotAdapter.ADAPTER.sendComponent(sender, string, bypass);
        sendMessage(sender, string + bypass); // griefus
    }

    public static void sendComponent(CommandSender sender, String string) {
        //sendComponent(sender, string, null);
        sendMessage(sender, string); // griefus
    }

    public static void sendMessage(CommandSender sender, String message) {
        // griefus start
        try {
            MiniMessage miniMessage = MiniMessage.miniMessage();
            sender.sendMessage(miniMessage.deserialize(message));
            return;
        } catch (ParsingException ignored) { }
        // griefus end

        if (sender instanceof ConsoleCommandSender) {
            message = message.replace(Color.DARK_AQUA, ChatColor.DARK_AQUA.toString());
        }

        sender.sendMessage(message);

    }

    public static void sendConsoleMessage(String string) {
        Bukkit.getServer().getConsoleSender().sendMessage(string);
    }

    public static void console(String string) {
        if (string.startsWith("-") || string.startsWith("[")) {
            Bukkit.getLogger().log(Level.INFO, string);
        }
        else {
            Bukkit.getLogger().log(Level.INFO, "[Griefus] " + string);
        }
    }

    public static void sendGlobalMessage(CommandSender user, String string) {
        if (user instanceof ConsoleCommandSender) {
            sendMessage(user, Color.DARK_AQUA + "[Griefus] " + Color.WHITE + string);
            return;
        }

        Server server = Bukkit.getServer();
        server.getConsoleSender().sendMessage("[Griefus] " + string);
        for (Player player : server.getOnlinePlayers()) {
            if (player.isOp() && !player.getName().equals(user.getName())) {
                sendMessage(player, string);
            }
        }
        if (user instanceof Player) {
            if (((Player) user).isOnline()) {
                sendMessage(user, string);
            }
        }
    }

}
