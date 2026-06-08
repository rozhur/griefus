package net.coreprotect.utility;

public class NumberUtils {
    public static String inflect(long value, String var1, String var2, String var3, String var4) {
        if (value == 0) {
            return var4;
        }

        long r1;
        if (value % 10 == 0 || (value / 10) % 10 == 1 || (r1 = value % 10) > 4) {
            return var3;
        } else if (r1 != 1) {
            return var2;
        } else {
            return var1;
        }
    }

    public static String inflect(long value, String var1, String var2, String var3) {
        return inflect(value, var1, var2, var3, var3);
    }

    public static String inflect(long value, String var1, String var2) {
        return inflect(value, var1, var2, var2);
    }
}
