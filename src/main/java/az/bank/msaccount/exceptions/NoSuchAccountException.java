package az.bank.msaccount.exceptions;

public class NoSuchAccountException extends RuntimeException {
    public NoSuchAccountException() {
    }

    public NoSuchAccountException(String message) {
        super(message);
    }
}
