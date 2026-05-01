package net.coreprotect.utility;

import java.util.logging.Level;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.ParsingException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import net.coreprotect.spigot.SpigotAdapter;

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
        } catch (ParsingException ignored) {
            ignored.printStackTrace();
        }
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

    // @todo: make this go brrr (prepare minimessage instead of using sendMessage which does same thing for each player)
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

    // Broadcast a message for everyone except the sender - Griefus
    public static void broadcastNoSender(CommandSender user, String permission, String string) {
        Component components;
        try {
            MiniMessage miniMessage = MiniMessage.miniMessage();
            components = miniMessage.deserialize(string);
        } catch (ParsingException e) {
            e.printStackTrace();
            return;
        }
        if (!(user instanceof ConsoleCommandSender)) {
            Bukkit.getConsoleSender().sendMessage(components); // not using console here as we're using components
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.hasPermission(permission)) continue;
            player.sendMessage(components);
        }
    }
}
