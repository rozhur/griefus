package net.coreprotect.command.parser;

import net.coreprotect.CoreProtect;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser for the message (m) filter argument - Griefus
 * TODO: support escaping quotes
 */
public class MessageParser {

    /**
     * Parse a message from the argument array
     * @param argumentArray arguments
     * @return message in this argument array
     */
    public static String parseMessage(String[] argumentArray) {
        boolean found = false;
        StringBuilder outputMessage = new StringBuilder();
        boolean quoted = false;
        boolean finish = false;
        int pos = 0;
        for (String argument : argumentArray) {
            if (finish) break;
            if (!found && (argument.startsWith("m:") || argument.startsWith("message:"))){
                found = true;
                quoted = argument.indexOf('\"') > -1;
                String begin = argument.replace("m:", "").replace("message:", "");
                if (quoted) {
                    begin = begin.substring(begin.indexOf('\"') + 1);
                    if (begin.indexOf('\"') > -1) {
                        finish = true;
                        begin = begin.substring(0, begin.indexOf('\"'));
                    }
                }
                outputMessage.append(begin);
                continue;
            }
            if (quoted) {
                if (argument.indexOf('\"') > -1) {
                    argument = argument.substring(0, argument.indexOf('\"'));
                    finish = true;
                }
                outputMessage.append(" ").append(argument);
                argumentArray[pos] = ""; // Removing additional arguments as they interfere with other params
            }
            pos++;
        }
        if (!found) return null;
        return outputMessage.toString();
    }
}
