package exception;

public class InvalidDOBException extends RuntimeException {
    final String error="Invalid DOB";

    public InvalidDOBException() {
        System.out.println(error);
        System.exit(1);
    }
}
