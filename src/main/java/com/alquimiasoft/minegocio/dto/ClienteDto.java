package com.alquimiasoft.minegocio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {

    @Schema(hidden = true)
    private Integer id;

    @NotBlank(message = "no puede ser nulo, ní vacío")
    @Size(max = 30, message = "no puede ser mayor a 30 caracteres")
    private String tipoIdentificacion;

    @NotBlank(message = "no puede ser nulo, ní vacío")
    @Size(max = 12, message = "no puede ser mayor a 12 caracteres")
    private String numeroIdentificacion;

    @NotBlank(message = "no puede ser nulo, ní vacío")
    @Size(max = 100, message = "no puede ser mayor a 100 caracteres")
    private String nombres;

    @Size(max = 75, message = "no puede ser mayor a 75 caracteres")
    private String correo;

    @Size(max = 10, message = "no puede ser mayor a 10 caracteres")
    private String telefono;

    private List<SucursalDto> sucursales;
}
