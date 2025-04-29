package com.alquimiasoft.minegocio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SucursalDto {

    @Schema(hidden = true)
    private Integer id;

    @NotBlank(message = "no puede ser nulo, ní vacío")
    @Size(max = 100, message = "no puede ser mayor a 100 caracteres")
    private String provincia;

    @NotBlank(message = "no puede ser nulo, ní vacío")
    @Size(max = 100, message = "no puede ser mayor a 100 caracteres")
    private String ciudad;

    @Size(max = 100, message = "no puede ser mayor a 100 caracteres")
    private String direccion;

    @Schema(hidden = true)
    private Boolean matriz;
}
