import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordValidationTest {
    @ParameterizedTest
    @MethodSource
    public void testHasMinimumLength(int minimumLength, String password, boolean expected){
        boolean actual = PasswordValidator.isOfMinimumLength(minimumLength, password);

        assertEquals(actual, expected);
    }

    private static Stream<Arguments> testHasMinimumLength(){
        return Stream.of(
                Arguments.of(8, "1234567", false),
                Arguments.of(8, "12345678", true),
                Arguments.of(8, "123456789", true)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testContainsNumbers(String password, boolean expected){
        boolean actual = PasswordValidator.containsNumbers(password);

        assertEquals(actual, expected);
    }

    private static Stream<Arguments> testContainsNumbers(){
        return Stream.of(
                Arguments.of("passwort", false),
                Arguments.of("p4sswort", true)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testContainsUppercaseAndLowercaseLetters(String password, boolean expected){
        boolean actual = PasswordValidator.containsUppercaseAndLowercaseLetters(password);

        assertEquals(actual, expected);
    }

    private static Stream<Arguments> testContainsUppercaseAndLowercaseLetters(){
        return Stream.of(
                Arguments.of("passwort", false),
                Arguments.of("PassWort", true),
                Arguments.of("PASSWORT", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testValidate(int minimumLength, String password, boolean expected){
        boolean actual = PasswordValidator.validate(minimumLength, password);

        assertEquals(actual, expected);
    }

    private static Stream<Arguments> testValidate(){
        return Stream.of(
                Arguments.of(8, "passWort", false),
                Arguments.of(8, "passw0rt", false),
                Arguments.of(8, "PassW0rt", true)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testValidateMultiplePasswords(int minimumLength, String[] passwords, boolean expected){
        boolean actual = PasswordValidator.validate(minimumLength, passwords);

        assertEquals(actual, expected);
    }

    private static Stream<Arguments> testValidateMultiplePasswords(){
        return Stream.of(
                Arguments.of(
                    8, new String[]{"passW0rt", "SuperPasswort42", "passwort"}, false
                ),
                Arguments.of(
                        8, new String[]{"passW0rt", "SuperPasswort42", "Passwort123"}, true
                )
        );
    }
}
