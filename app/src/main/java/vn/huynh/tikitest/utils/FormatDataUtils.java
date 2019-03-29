package vn.huynh.tikitest.utils;

/**
 * Created by duong on 3/28/2019.
 */

public class FormatDataUtils {
    /**
     * Format the keyword according to the rule
     *
     * @param s the original keyword
     * @return the formatted keyword (with break line)
     */
    public static String formatKeyword(String s) {
        if (s.isEmpty())
            return s;
        s = s.trim().replace("  ", " ").replace("\n", "");
        if (!s.contains(" "))
            return s;
        return addBreakLine(s);
    }

    /**
     * Add the break line to the keyword at the reasonable position
     *
     * @param s the keyword that was trimmed
     * @return the keyword after adding break line
     */
    public static String addBreakLine(String s) {

        /**
         * Assuming that we have the input is: "Dac nhan tam"
         * We have 2 ways to display it
         *     Dac nhan
         *     tam
         * or
         *     Dac
         *     nhan tam
         * In the sample gif, I think it's the first way.
         * So currently in this function I made it the first way.
         * But I prefer the second way, just change the middlePos value
         *     middlePos = (s.length() - 1) /2;
         */
        int middlePos = s.length() / 2;
        char[] chars = s.toCharArray();

        if (s.charAt(middlePos) == ' ') {
            chars[middlePos] = '\n';
            return String.valueOf(chars);
        }

        for (int i = 1; i <= middlePos; i++) {
            if (s.charAt(middlePos - i) == ' ') {
                chars[middlePos - i] = '\n';
                return String.valueOf(chars);
            } else if (s.charAt(middlePos + i) == ' ') {
                chars[middlePos + i] = '\n';
                return String.valueOf(chars);
            }
        }
        return s;
    }
}
