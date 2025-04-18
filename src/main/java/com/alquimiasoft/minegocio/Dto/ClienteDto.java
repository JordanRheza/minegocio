package com.alquimiasoft.minegocio.Dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ClienteDto {

    private Integer id;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombres;
    private String correo;
    private String telefono;
}
