package com.alquimiasoft.minegocio.Controller;

import com.alquimiasoft.minegocio.Exception.RecursoNoEncontrado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MsjException {
    @ExceptionHandler(RecursoNoEncontrado.class)
    public ResponseEntity<Map<String, Object>> manejarException(RecursoNoEncontrado ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("Mensaje", ex.getMessage());
        response.put("Estado", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
