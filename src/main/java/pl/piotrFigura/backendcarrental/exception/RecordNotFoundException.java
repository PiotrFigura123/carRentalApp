package pl.piotrFigura.backendcarrental.exception;

/**
 * Wyjątek dotyczący braku znalezienia rekordu w bazie.
 */

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String message) {
        super(message);
    }

}
