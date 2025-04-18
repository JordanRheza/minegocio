package com.alquimiasoft.minegocio.Service;

import com.alquimiasoft.minegocio.Dto.ClienteDto;
import com.alquimiasoft.minegocio.Model.Cliente;

import java.util.List;

public interface IClienteService {

    public List<ClienteDto> listarClientes();
    public ClienteDto buscarClientePorId(Integer id);
    public Cliente guardarCliente(Cliente cliente);
    public void eliminarClientePorId(Integer id);
}