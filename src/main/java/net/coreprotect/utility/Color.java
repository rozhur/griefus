package net.coreprotect.utility;

//import org.bukkit.ChatColor;

public final class Color {

    // we define our own constants here to eliminate string concatenation
    // javadoc taken from org.bukkit.ChatColor

    /**
     * Represents black.
     */
    //public static final String BLACK = ChatColor.BLACK.toString();
    public static final String BLACK = "<black>"; // griefus

    /**
     * Represents dark blue.
     */
    //public static final String DARK_BLUE = ChatColor.DARK_BLUE.toString();
    public static final String DARK_BLUE = "<dark_blue>"; // griefus

    /**
     * Represents dark green.
     */
    //public static final String DARK_GREEN = ChatColor.DARK_GREEN.toString();
    public static final String DARK_GREEN = "<dark_green>"; // griefus

    /**
     * Represents dark blue (aqua).
     */
    //public static String DARK_AQUA = ChatColor.DARK_AQUA.toString();
    public static String DARK_AQUA = "<dark_aqua>"; // griefus

    /**
     * Represents dark red.
     */
    //public static final String DARK_RED = ChatColor.DARK_RED.toString();
    public static final String DARK_RED = "<dark_red>"; // griefus

    /**
     * Represents dark purple.
     */
    //public static final String DARK_PURPLE = ChatColor.DARK_PURPLE.toString();
    public static final String DARK_PURPLE = "<dark_purple>"; // griefus

    /**
     * Represents gold.
     */
    //public static final String GOLD = ChatColor.GOLD.toString();
    public static final String GOLD = "<gold>"; // griefus

    /**
     * Represents grey.
     */
    //public static final String GREY = ChatColor.GRAY.toString();
    public static final String GREY = "<gray>"; // griefus

    /**
     * Represents dark grey.
     */
    //public static final String DARK_GREY = ChatColor.DARK_GRAY.toString();
    public static final String DARK_GREY = "<dark_gray>"; // griefus

    /**
     * Represents blue.
     */
    //public static final String BLUE = ChatColor.BLUE.toString();
    public static final String BLUE = "<blue>"; // griefus

    /**
     * Represents green.
     */
    //public static final String GREEN = ChatColor.GREEN.toString();
    public static final String GREEN = "<green>"; // griefus

    /**
     * Represents aqua.
     */
    //public static final String AQUA = ChatColor.AQUA.toString();
    public static final String AQUA = "<aqua>"; // griefus

    /**
     * Represents red.
     */
    //public static final String RED = ChatColor.RED.toString();
    public static final String RED = "<red>"; // griefus

    /**
     * Represents light purple.
     */
    //public static final String LIGHT_PURPLE = ChatColor.LIGHT_PURPLE.toString();
    public static final String LIGHT_PURPLE = "<light_purple>"; // griefus

    /**
     * Represents yellow.
     */
    //public static final String YELLOW = ChatColor.YELLOW.toString();
    public static final String YELLOW = "<yellow>"; // griefus

    /**
     * Represents white.
     */
    //public static final String WHITE = ChatColor.WHITE.toString();
    public static final String WHITE = "<white>"; // griefus

    /**
     * Represents magical characters that change around randomly.
     */
    //public static final String MAGIC = ChatColor.COLOR_CHAR + "k";
    public static final String MAGIC = "<obfuscated>"; // griefus

    /**
     * Makes the text bold.
     */
    //public static final String BOLD = ChatColor.COLOR_CHAR + "l";
    public static final String BOLD = "<bold>"; // griefus

    /**
     * Makes a line appear through the text.
     */
    //public static final String STRIKETHROUGH = ChatColor.COLOR_CHAR + "m";
    public static final String STRIKETHROUGH = "<strikethrough>"; // griefus

    /**
     * Makes the text appear underlined.
     */
    //public static final String UNDERLINE = ChatColor.COLOR_CHAR + "n";
    public static final String UNDERLINE = "<underlined>"; // griefus

    /**
     * Makes the text italic.
     */
    //public static final String ITALIC = ChatColor.COLOR_CHAR + "o";
    public static final String ITALIC = "<italic>"; // griefus

    /**
     * Resets all previous chat colors or formats.
     */
    //public static final String RESET = ChatColor.COLOR_CHAR + "r";
    public static final String RESET = "<reset>"; // griefus

    private Color() {
        throw new IllegalStateException("Utility class");
    }

}
