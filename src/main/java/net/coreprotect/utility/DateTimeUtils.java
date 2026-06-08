package net.coreprotect.utility;

import net.coreprotect.language.Phrase;

import static net.coreprotect.language.Phrase.*;

import java.util.concurrent.TimeUnit;

public class DateTimeUtils {
    public static final long ONE_MINUTE = TimeUnit.MINUTES.toMillis(1);
    public static final long ONE_HOUR = TimeUnit.HOURS.toMillis(1);
    public static final long FOUR_HOURS = ONE_HOUR * 5;
    public static final long ONE_DAY = TimeUnit.DAYS.toMillis(1);
    public static final long TWO_DAYS = ONE_DAY * 2;
    public static final long WEEK = ONE_DAY * 7;

    private static String inflect(long value,
            Phrase s1, Phrase s2, Phrase s3,
            Phrase m1, Phrase m2, Phrase m3,
            Phrase h1, Phrase h2, Phrase h3,
            Phrase d1, Phrase d2, Phrase d3, Phrase now, TimeUnit preferred) {
        if (value < 1000) {
            return build(now);
        }

        StringBuilder builder = new StringBuilder();
        if (value < ONE_MINUTE || preferred == TimeUnit.SECONDS) {
            long seconds = TimeUnit.MILLISECONDS.toSeconds(value);
            builder.append(NumberUtils.inflect(seconds, build(s1, seconds), build(s2, seconds), build(s3, seconds)));
        } else if (value < ONE_HOUR || preferred == TimeUnit.MINUTES) {
            long minutes = TimeUnit.MILLISECONDS.toMinutes(value);
            long seconds = TimeUnit.MILLISECONDS.toSeconds(value) - TimeUnit.MINUTES.toSeconds(minutes);
            builder.append(NumberUtils.inflect(minutes, build(m1, minutes), build(m2, minutes), build(m3, minutes)));
            if (seconds > 0)
                builder.append(NumberUtils.inflect(seconds, build(s1, seconds), build(s2, seconds), build(s3, seconds)));
        } else if (value < ONE_DAY || preferred == TimeUnit.HOURS) {
            long hours = TimeUnit.MILLISECONDS.toHours(value);
            long minutes = TimeUnit.MILLISECONDS.toMinutes(value) - TimeUnit.HOURS.toMinutes(hours);
            builder.append(NumberUtils.inflect(hours, build(h1, hours), build(h2, hours), build(h3, hours)));
            if (minutes > 0)
                builder.append(NumberUtils.inflect(minutes, build(m1, minutes), build(m2, minutes), build(m3, minutes)));
        } else {
            long days = TimeUnit.MILLISECONDS.toDays(value);
            long hours = TimeUnit.MILLISECONDS.toHours(value) - TimeUnit.DAYS.toHours(days);
            builder.append(NumberUtils.inflect(days, build(d1, days), build(d2, days), build(d3, days)));
            if (hours > 0)
                builder.append(NumberUtils.inflect(hours, build(h1, hours), build(h2, hours), build(h3, hours)));
        }

        return builder.toString();
    }

    public static String inflect(long value, Phrase now, TimeUnit preferred) {
        return inflect(value, 
                SECOND, SECONDS_ALT, SECONDS,
                MINUTE, MINUTES_ALT, MINUTES,
                HOUR, HOURS_ALT, HOURS,
                DAY, DAYS_ALT, DAYS, now, preferred);
    }

    public static String inflect(long value, TimeUnit preferred) {
        return inflect(value, NOW, preferred);
    }

    public static String inflect(long value) {
        return inflect(value, TimeUnit.DAYS);
    }

    public static String inflectAlt(long value, Phrase now, TimeUnit preferred) {
        return inflect(value,
                SECOND_ALT, SECONDS_ALT, SECONDS,
                MINUTE_ALT, MINUTES_ALT, MINUTES,
                HOUR_ALT, HOURS_ALT, HOURS,
                DAY_ALT, DAYS_ALT, DAYS, now, preferred);
    }

    public static String inflectAlt(long value, TimeUnit preferred) {
        return inflectAlt(value, NOW, preferred);
    }

    public static String inflectAlt(long value) {
        return inflectAlt(value, TimeUnit.DAYS);
    }
}
