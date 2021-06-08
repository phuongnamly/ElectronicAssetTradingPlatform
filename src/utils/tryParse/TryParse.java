package utils.tryParse;

/**
 * This is the class for parsing String into Int
 */
public class TryParse {
    public static Integer TryParseInt(String someText) {
        try {
            return Integer.parseInt(someText);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}

