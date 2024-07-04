package it.einjojo.akani.util.text;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Utility class for text manipulation
 */
public class TextUtil {
    private static final Map<Character, String> LEGACY_MINIMESSAGE_MAP;
    public static final char AMPERSAND = '&';
    public static final char SECTION = '§';

    static {
        LEGACY_MINIMESSAGE_MAP = new HashMap<>();
        LEGACY_MINIMESSAGE_MAP.put('a', "<green>");
        LEGACY_MINIMESSAGE_MAP.put('b', "<aqua>");
        LEGACY_MINIMESSAGE_MAP.put('c', "<red>");
        LEGACY_MINIMESSAGE_MAP.put('d', "<light_purple>");
        LEGACY_MINIMESSAGE_MAP.put('e', "<yellow>");
        LEGACY_MINIMESSAGE_MAP.put('f', "<white>");
        LEGACY_MINIMESSAGE_MAP.put('0', "<black>");
        LEGACY_MINIMESSAGE_MAP.put('1', "<dark_blue>");
        LEGACY_MINIMESSAGE_MAP.put('2', "<dark_green>");
        LEGACY_MINIMESSAGE_MAP.put('3', "<dark_aqua>");
        LEGACY_MINIMESSAGE_MAP.put('4', "<dark_red>");
        LEGACY_MINIMESSAGE_MAP.put('5', "<dark_purple>");
        LEGACY_MINIMESSAGE_MAP.put('6', "<gold>");
        LEGACY_MINIMESSAGE_MAP.put('7', "<gray>");
        LEGACY_MINIMESSAGE_MAP.put('8', "<dark_gray>");
        LEGACY_MINIMESSAGE_MAP.put('9', "<blue>");
        LEGACY_MINIMESSAGE_MAP.put('k', "<obfuscated>");
        LEGACY_MINIMESSAGE_MAP.put('l', "<bold>");
        LEGACY_MINIMESSAGE_MAP.put('m', "<strikethrough>");
        LEGACY_MINIMESSAGE_MAP.put('o', "<italic>");
        LEGACY_MINIMESSAGE_MAP.put('r', "<reset>");
    }


    public static String transformToMiniMessage(String s) {
        return transformToMiniMessage(c -> c == AMPERSAND || c == SECTION, s);
    }

    public static String transformToMiniMessage(char prefix, String s) {
        return transformToMiniMessage(c -> c == prefix, s);
    }

    /**
     * Efficiently transform a string with ampersand color codes to MiniMessage format
     *
     * @param s The string to transform
     * @return The transformed string
     */
    public static String transformToMiniMessage(Predicate<Character> prefixMatch, String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (prefixMatch.test(c) && i < s.length() - 1) {
                char code = s.charAt(i + 1);
                String replacement = LEGACY_MINIMESSAGE_MAP.get(code);
                if (replacement != null) {
                    sb.append(replacement);
                    i++; // Skip the next character
                    continue;
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }


}
