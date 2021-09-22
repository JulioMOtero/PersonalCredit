package br.com.otero.parkinglot.exception;

import br.com.otero.parkinglot.models.dto.ErroResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        final ErroResponse erroResponse = ErroResponse.create(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return this.buildResponseEntity(erroResponse);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        final ErroResponse erroResponse = ErroResponse.create(HttpStatus.NOT_FOUND, ex.getMessage());
        return this.buildResponseEntity(erroResponse);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        final ErroResponse erroResponse = ErroResponse.create(HttpStatus.BAD_REQUEST, ex.getMessage());
        return this.buildResponseEntity(erroResponse);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception ex) {
        final ErroResponse erroResponse = ErroResponse.create(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return this.buildResponseEntity(erroResponse);
    }

    private ResponseEntity<Object> buildResponseEntity(ErroResponse erroResponse) {
        return new ResponseEntity<>(erroResponse, erroResponse.getStatus());
    }

}
