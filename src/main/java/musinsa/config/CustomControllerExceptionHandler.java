package musinsa.config;


import java.util.NoSuchElementException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomControllerExceptionHandler {

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException e) {

        ErrorResponse errorResponse = ErrorResponse.builder().message(e.getMessage()).build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException e) {

        String message = e.getMessage();

        if("category".equals(e.getParameterName())) {
            message = "존재하지 않는 카테고리입니다.";
        }

        ErrorResponse errorResponse = ErrorResponse.builder().message(message).build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {NoHandlerFoundException.class, NoResourceFoundException.class})
    public ResponseEntity<ErrorResponse> expectedException(Exception e) {

        ErrorResponse errorResponse = ErrorResponse.builder().message("존재 하지 않는 URL 입니다.").build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
