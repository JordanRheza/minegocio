package com.alquimiasoft.minegocio.handlers;

import com.alquimiasoft.minegocio.exception.ConflicException;
import com.alquimiasoft.minegocio.exception.ErrorResponseDto;
import com.alquimiasoft.minegocio.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> notFoundException(NotFoundException e) {
        ErrorResponseDto responseDto = new ErrorResponseDto(
                "Not Found",
                e.getMessage(),
                "404"
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> hadleException(Exception e) {
        ErrorResponseDto responseDto = new ErrorResponseDto(
                "Internal Server Error",
                e.getMessage(),
                "500");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationException(MethodArgumentNotValidException e) {
        var error = e.getBindingResult().getFieldErrors().get(0);
        String campo = error.getField();
        String mensaje = error.getDefaultMessage();
        String mensajeUsuario = String.format("El campo '%s' %s", campo, mensaje);

        ErrorResponseDto responseDto = new ErrorResponseDto(
                "Bad Request",
                mensajeUsuario,
                "400"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }


    @ExceptionHandler(ConflicException.class)
    public ResponseEntity<ErrorResponseDto> ConflicException(Exception e) {
        ErrorResponseDto responseDto = new ErrorResponseDto(
                "Conflict",
                e.getMessage(),
                "409");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseDto);
    }
}
