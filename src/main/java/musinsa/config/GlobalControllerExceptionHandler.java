package musinsa.config;


import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Order()
@ControllerAdvice
public class GlobalControllerExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> unexpectedException(Exception e) {

        ErrorResponse errorResponse = ErrorResponse.builder().message("서버 내부 오류").build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
