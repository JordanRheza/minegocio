package com.alquimiasoft.minegocio.service;

import com.alquimiasoft.minegocio.dto.ClienteDto;
import com.alquimiasoft.minegocio.exception.NotFoundException;

import java.util.List;

public interface IClienteService {

    List<ClienteDto> listarClientes() throws NotFoundException;
    ClienteDto guardarCliente(ClienteDto clienteDto);
    void eliminarClientePorId(Integer id) throws NotFoundException;
}