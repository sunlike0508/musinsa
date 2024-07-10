package musinsa.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Order()
@ControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> unexpectedException(Exception e) {

        log.error(e.getMessage());

        return new ResponseEntity<>("서버 오류 입니다", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
