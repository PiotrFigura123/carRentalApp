package pl.piotrFigura.backendcarrental.exception;

public class NoAvalaibleCarsException extends RuntimeException{

    public NoAvalaibleCarsException(String message) {
    super(message);
    }
}
