package net.coreprotect.command;

import java.util.Locale;

import org.bukkit.command.CommandSender;

import net.coreprotect.language.Phrase;
import net.coreprotect.utility.Chat;
import net.coreprotect.utility.Color;

public class HelpCommand {
    protected static void runCommand(CommandSender player, boolean permission, String label, String[] args) {
        int resultc = args.length;
        if (permission) {
            if (resultc > 1) {
                String helpcommand_original = args[1];
                String helpcommand = args[1].toLowerCase(Locale.ROOT);
                helpcommand = helpcommand.replaceAll("[^a-zA-Z]", "");
                Chat.sendMessage(player, Phrase.build(Phrase.HELP_HEADER));
                if (helpcommand.equals("help")) {
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_CO_HELP));
                } else if (helpcommand.equals("inspect") || helpcommand.equals("inspector") || helpcommand.equals("in")) {
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_INSPECT_1));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_INSPECT_2));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_INSPECT_3));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_INSPECT_4));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_INSPECT_5));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_INSPECT_6));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_INSPECT_7));
                } else if (helpcommand.equals("params") || helpcommand.equals("param") || helpcommand.equals("parameters") || helpcommand.equals("parameter")) {
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_PARAMS_HEADER));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_PARAMS_USER));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_PARAMS_TIME));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_PARAMS_RADIUS));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_PARAMS_ACTION));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_PARAMS_INCLUDE));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_PARAMS_EXCLUDE));
                } else if (helpcommand.equals("rollback") || helpcommand.equals("rollbacks") || helpcommand.equals("rb") || helpcommand.equals("ro")) {
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_ROLLBACK_HEADER));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_ROLLBACK_USER));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_ROLLBACK_TIME));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_ROLLBACK_RADIUS));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_ROLLBACK_ACTION));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_ROLLBACK_INCLUDE));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_ROLLBACK_EXCLUDE));
                } else if (helpcommand.equals("restore") || helpcommand.equals("restores") || helpcommand.equals("re") || helpcommand.equals("rs")) {
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_RESTORE_HEADER));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_RESTORE_USER));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_RESTORE_TIME));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_RESTORE_RADIUS));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_RESTORE_ACTION));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_RESTORE_INCLUDE));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_RESTORE_EXCLUDE));
                } else if (helpcommand.equals("lookup") || helpcommand.equals("lookups") || helpcommand.equals("l")) {
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_LOOKUP_HEADER, Color.DARK_AQUA));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_LOOKUP_ALIAS));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_LOOKUP_PAGE));
                } else if (helpcommand.equals("purge") || helpcommand.equals("purges")) {
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_PURGE_MAIN));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_PURGE_EXAMPLE));
                } else if (helpcommand.equals("reload")) {
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_RELOAD));
                } else if (helpcommand.equals("status")) {
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_STATUS));
                } else if (helpcommand.equals("teleport")) {
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_TELEPORT));
                } else if (helpcommand.equals("u") || helpcommand.equals("user") || helpcommand.equals("users") || helpcommand.equals("uuser") || helpcommand.equals("uusers")) {
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_USER_MAIN));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_USER_NOTE));
                } else if (helpcommand.equals("t") || helpcommand.equals("time") || helpcommand.equals("ttime")) {
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_TIME_MAIN));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_TIME_NOTE));
                } else if (helpcommand.equals("r") || helpcommand.equals("radius") || helpcommand.equals("rradius")) {
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_RADIUS_MAIN));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_RADIUS_NOTE));
                } else if (helpcommand.equals("a") || helpcommand.equals("action") || helpcommand.equals("actions") || helpcommand.equals("aaction")) {
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_ACTION_MAIN));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_ACTION_NOTE));
                } else if (helpcommand.equals("i") || helpcommand.equals("include") || helpcommand.equals("iinclude") || helpcommand.equals("b") || helpcommand.equals("block") || helpcommand.equals("blocks") || helpcommand.equals("bblock") || helpcommand.equals("bblocks")) {
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_INCLUDE_MAIN));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_INCLUDE_NOTE));
                    Chat.sendMessage(player, Phrase.build(Phrase.LINK_WIKI_BLOCK));
                    Chat.sendMessage(player, Phrase.build(Phrase.LINK_WIKI_ENTITY));
                } else if (helpcommand.equals("e") || helpcommand.equals("exclude") || helpcommand.equals("eexclude")) {
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_EXCLUDE_MAIN));
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_EXCLUDE_NOTE));
                    Chat.sendMessage(player, Phrase.build(Phrase.LINK_WIKI_BLOCK));
                } else {
                    Chat.sendMessage(player, Phrase.build(Phrase.HELP_NO_INFO, Color.WHITE, helpcommand_original));
                }
            } else {
                Chat.sendMessage(player, Phrase.build(Phrase.HELP_MAIN_HEADER));
                Chat.sendMessage(player, Phrase.build(Phrase.HELP_MAIN_COMMAND));
                Chat.sendMessage(player, Phrase.build(Phrase.HELP_MAIN_INSPECT));
                Chat.sendMessage(player, Phrase.build(Phrase.HELP_MAIN_ROLLBACK));
                Chat.sendMessage(player, Phrase.build(Phrase.HELP_MAIN_RESTORE));
                Chat.sendMessage(player, Phrase.build(Phrase.HELP_MAIN_LOOKUP));
                Chat.sendMessage(player, Phrase.build(Phrase.HELP_MAIN_PURGE));
                Chat.sendMessage(player, Phrase.build(Phrase.HELP_MAIN_RELOAD));
                Chat.sendMessage(player, Phrase.build(Phrase.HELP_MAIN_STATUS));
            }
        } else {
            Chat.sendMessage(player, Phrase.build(Phrase.NO_PERMISSION));
        }
    }
}
