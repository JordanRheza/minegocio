package com.alquimiasoft.minegocio.Service;

import com.alquimiasoft.minegocio.Dto.ClienteDto;
import com.alquimiasoft.minegocio.Exception.RecursoNoEncontrado;
import com.alquimiasoft.minegocio.Model.Cliente;
import com.alquimiasoft.minegocio.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService implements IClienteService{

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public List<ClienteDto> listarClientes() {
        return this.clienteRepository.findAll().stream()
                .map(this::conversionADto)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDto buscarClientePorId(Integer id) {
        return this.clienteRepository.findById(id)
                .map(this::conversionADto)
                .orElseThrow(() -> new RecursoNoEncontrado("No se encontró el cliente con el ID: " + id));
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    @Override
    public void eliminarClientePorId(Integer id) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);

        if (clienteOpt.isPresent()) {
            clienteRepository.deleteById(id);
        } else {
            throw new RecursoNoEncontrado("No se encontró el cliente con el ID: " + id);
        }
    }

    public ClienteDto actualizarClientePorId(Integer id, Cliente clienteRequest) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);

        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            cliente.setTipoIdentificacion(clienteRequest.getTipoIdentificacion());
            cliente.setNumeroIdentificacion(clienteRequest.getNumeroIdentificacion());
            cliente.setNombres(clienteRequest.getNombres());
            cliente.setCorreo(clienteRequest.getCorreo());
            cliente.setTelefono(clienteRequest.getTelefono());

            Cliente clienteActualizado = clienteRepository.save(cliente);

            return conversionADto(clienteActualizado);
        } else {
            throw new RecursoNoEncontrado("No se encontró el cliente con el ID: " + id);
        }
    }

    //Conversion de Modelo a Dto
    private ClienteDto conversionADto(Cliente cliente) {
        return new ClienteDto(
                cliente.getId(),
                cliente.getTipoIdentificacion(),
                cliente.getNumeroIdentificacion(),
                cliente.getNombres(),
                cliente.getCorreo(),
                cliente.getTelefono()
        );
    }
}
