package net.coreprotect.command.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Parser for the message (m) filter argument - Griefus
 */
public class MessageParser {

    /**
     * Parse a message from the argument array
     * @param argumentArray arguments
     * @return message in this argument array
     */
    public static List<String> parseMessage(String[] argumentArray) {
        List<String> argMessages = new ArrayList<>();
        for (String argument : argumentArray) {
            int index;
            if ((index = argument.indexOf("m:")) != -1 || (index = argument.indexOf("message:")) != -1) {
                argMessages.add(argument.substring(argument.indexOf(':', index) + 1));
            }
        }
        return argMessages;
    }
}
