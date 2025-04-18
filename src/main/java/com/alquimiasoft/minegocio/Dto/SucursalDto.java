package com.alquimiasoft.minegocio.Dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class SucursalDto {

    private Integer id;
    private String provincia;
    private String ciudad;
    private String direccion;
}
