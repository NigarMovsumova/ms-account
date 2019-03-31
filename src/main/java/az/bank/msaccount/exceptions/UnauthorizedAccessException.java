package az.bank.msaccount.exceptions;

public class UnauthorizedAccessException extends RuntimeException {
    public UnauthorizedAccessException() {
    }

    public UnauthorizedAccessException(String message) {
        super(message);
    }

}
