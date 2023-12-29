package pl.piotrFigura.backendcarrental.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice(annotations = RecordNotFoundProcessing.class)
public class ApiExceptionHandler {

    @ExceptionHandler(value = {RecordNotFoundException.class})
    public ResponseEntity<Object> handleRecordNotFoudException(RecordNotFoundException e) {
        var badRequest = HttpStatus.BAD_REQUEST;
        var apiException = new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {NotCorrectBodyException.class})
    public ResponseEntity<Object> handleNotCorrectBodyException(NotCorrectBodyException e) {
        var badRequest = HttpStatus.BAD_REQUEST;
        var apiException = new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, badRequest);
    }
}
