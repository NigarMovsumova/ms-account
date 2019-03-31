package az.bank.msaccount.exceptions;

public class NoAccountOperationsException extends RuntimeException {
    public NoAccountOperationsException() {
    }

    public NoAccountOperationsException(String message) {
        super(message);
    }
}

