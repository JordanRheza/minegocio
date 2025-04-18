package com.alquimiasoft.minegocio.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {

    private Integer id;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombres;
    private String correo;
    private String telefono;


}
