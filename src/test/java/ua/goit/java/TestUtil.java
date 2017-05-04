package ua.goit.java;

/**
 * @author Petri Kainulainen
 */
public class TestUtil {

    private static final String CHARACTER = "a";

    public static String createRedirectViewPath(String path) {
        return "redirect:" + path;
    }

    public static String createStringWithLength(int length) {
        StringBuilder builder = new StringBuilder();

        for (int index = 0; index < length; index++) {
            builder.append(CHARACTER);
        }

        return builder.toString();
    }
}
