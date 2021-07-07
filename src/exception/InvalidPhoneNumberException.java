package exception;

public class InvalidPhoneNumberException extends RuntimeException {
    final String error="Invalid phone number";

    public InvalidPhoneNumberException() {
        System.out.println(error);System.exit(1);
    }
}
