package musinsa.config;


import java.util.NoSuchElementException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomControllerExceptionHandler {

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<ErrorResponse> handleDataNotFoundException(NoSuchElementException e) {

        ErrorResponse errorResponse = ErrorResponse.builder().message(e.getMessage()).build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
