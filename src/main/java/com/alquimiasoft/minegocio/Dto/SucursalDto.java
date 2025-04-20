package com.alquimiasoft.minegocio.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SucursalDto {

    private Integer id;
    private String provincia;
    private String ciudad;
    private String direccion;
}
