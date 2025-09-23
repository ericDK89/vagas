package br.com.eric.vagas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<ErrorMessageDTO> dto = ex.getBindingResult().getFieldErrors().stream().map(err ->
                new ErrorMessageDTO(err.getDefaultMessage(), err.getField())).toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }
}
