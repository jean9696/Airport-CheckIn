package exception;

public class InvalidInputException extends Exception {
    public InvalidInputException(String e) {
        super("Invalid input " + e);
    }
}
