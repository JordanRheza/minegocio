package com.alquimiasoft.minegocio.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.util.List;

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

    private List<SucursalDto> sucursales;
}
