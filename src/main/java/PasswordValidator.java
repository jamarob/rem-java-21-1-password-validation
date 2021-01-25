public class PasswordValidator {
    public static boolean isOfMinimumLength(int minimumLength, String password) {
        return password.length() >= minimumLength;
    }

    private static final String DIGITS = "0123456789";

    public static boolean containsNumbers(String password) {
        for (int i = 0; i < DIGITS.length(); i++) {
            char digit = DIGITS.charAt(i);
            if (password.contains(String.valueOf(digit))) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsUppercaseAndLowercaseLetters(String password) {
        String lowercasePassword = password.toLowerCase();
        String uppercasePassword = password.toUpperCase();
        if (lowercasePassword.equals(password) || uppercasePassword.equals(password)) {
            return false;
        }
        return true;
    }

    public static boolean validate(int minimumLength, String password) {
        return isOfMinimumLength(minimumLength, password)
                && containsNumbers(password)
                && containsUppercaseAndLowercaseLetters(password);
    }

    public static boolean validate(int minimumLength, String[] passwords) {
        for (int i = 0; i < passwords.length; i++) {
            String password = passwords[i];
            boolean isValidate = validate(minimumLength, password);
            if(!isValidate) {
                return false;
            }
        }
        return true;
    }

}