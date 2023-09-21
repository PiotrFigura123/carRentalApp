package pl.piotrFigura.backendcarrental.exception;

public class NotCorrectBodyException extends RuntimeException {
    public NotCorrectBodyException(String message) {
        super(message);
    }
}
