package com.alquimiasoft.minegocio.service;

import com.alquimiasoft.minegocio.dto.SucursalDto;
import com.alquimiasoft.minegocio.exception.NotFoundException;

import java.util.List;

public interface ISucursalService {

    void agregarSucursal(Integer clienteId, SucursalDto sucursalDto) throws NotFoundException;
    List<SucursalDto> listarDirecciones(Integer clienteId);
}
