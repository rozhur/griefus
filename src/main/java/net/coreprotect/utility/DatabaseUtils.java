package net.coreprotect.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class DatabaseUtils {
    public static final String[] ESCAPE_CHARS = {"%", "_", "[", "]", "^", "\\", "'"};

    private DatabaseUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> entriesSortedByValues(Map<K, V> map) {
        SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<>((e1, e2) -> {
            int res = e1.getValue().compareTo(e2.getValue());
            return res != 0 ? res : 1;
        });
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }

    public static boolean successfulQuery(Connection connection, String query) {
        boolean result = false;
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            if (resultSet.isBeforeFirst()) {
                result = true;
            }
            resultSet.close();
            preparedStmt.close();
        }
        catch (Exception e) {
            ErrorReporter.report(e);
        }
        return result;
    }

    public static String escape(String input) {
        StringBuilder builder = new StringBuilder(input);
        for (String replacement : ESCAPE_CHARS) {
            int index = builder.indexOf(replacement);
            if (index == -1) {
                continue;
            }

            String result = '\\' + replacement;
            do {
                builder.replace(index, index + replacement.length(), result);
                index = builder.indexOf(replacement, index + result.length());
            } while (index != -1);
        }

        return builder.toString();
    }
}
