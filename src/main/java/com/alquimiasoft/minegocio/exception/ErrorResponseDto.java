package com.alquimiasoft.minegocio.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ErrorResponseDto {
    private String error;
    private String details;
    private String status;
}
