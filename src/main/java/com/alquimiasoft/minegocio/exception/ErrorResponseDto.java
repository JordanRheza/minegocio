package com.alquimiasoft.minegocio.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ErrorResponseDto {
    private String mensajeSistema;
    private String mensajeUsuario;
    private String estado;
}
