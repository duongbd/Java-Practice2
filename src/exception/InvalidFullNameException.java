package exception;

public class InvalidFullNameException extends RuntimeException {
    final String error="Invalid full name";

    public InvalidFullNameException() {
        System.out.println(error);
        System.exit(1);
    }
}
