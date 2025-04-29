package com.alquimiasoft.minegocio.service;

import com.alquimiasoft.minegocio.dto.ClienteDto;
import com.alquimiasoft.minegocio.dto.SucursalDto;
import com.alquimiasoft.minegocio.exception.ConflicException;
import com.alquimiasoft.minegocio.exception.NotFoundException;
import com.alquimiasoft.minegocio.model.Cliente;
import com.alquimiasoft.minegocio.model.Sucursal;
import com.alquimiasoft.minegocio.repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClienteService implements IClienteService{

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public List<ClienteDto> listarClientes() throws NotFoundException {
        List<ClienteDto> datosClientes;

            datosClientes = this.clienteRepository.findAll().stream()
                    .map((this::conversionADto))
                    .toList();
            if (datosClientes.isEmpty()) {
                throw new NotFoundException("No existen clientes registrados en la base de datos.");
            }

        return datosClientes;
    }

    public List<ClienteDto> buscarClientes(String numeroIdentificacion, String nombres) throws NotFoundException {
        if (numeroIdentificacion == null && nombres == null) {
            throw new IllegalArgumentException("El número de identificación y el nombre no pueden ser null.");
        }

        List<Cliente> cliente = this.clienteRepository.findByNumeroIdentificacionOrNombres(numeroIdentificacion, nombres);

        if (cliente.isEmpty()) {
            throw new NotFoundException("No exite el cliente en la base de datos.");
        }

        return cliente.stream().map(this::conversionADto).collect(Collectors.toList());
    }

    public ClienteDto guardarCliente(ClienteDto clienteDto) {
        // Validacion que no exista más de un cliente con el mismo número de identificación
        Optional<Cliente> clienteOpt = clienteRepository.findByNumeroIdentificacion(clienteDto.getNumeroIdentificacion());

        if (clienteOpt.isPresent()) {
            throw new ConflicException("Ya existe un cliente registrado con el número de identificación: " + clienteDto.getNumeroIdentificacion());
        }

        Cliente cliente = new Cliente();
        cliente.setId(clienteDto.getId());
        cliente.setTipoIdentificacion(clienteDto.getTipoIdentificacion());
        cliente.setNumeroIdentificacion(clienteDto.getNumeroIdentificacion());
        cliente.setNombres(clienteDto.getNombres());
        cliente.setCorreo(clienteDto.getCorreo());
        cliente.setTelefono(clienteDto.getTelefono());

        if (clienteDto.getSucursales() != null) {
            // variable para almacenar la sucursal matriz
            boolean matriz = true;
            for (SucursalDto sucursalDto : clienteDto.getSucursales()) {
                Sucursal sucursal = new Sucursal();
                sucursal.setProvincia(sucursalDto.getProvincia());
                sucursal.setCiudad(sucursalDto.getCiudad());
                sucursal.setDireccion(sucursalDto.getDireccion());
                sucursal.setMatriz(matriz);
                matriz = false;

                cliente.addSucursal(sucursal);
            }
        }
        Cliente clienteGuardado = clienteRepository.save(cliente);
        return conversionADto(clienteGuardado);
    }

    public ClienteDto actualizarClientePorId(Integer id, ClienteDto clienteRequest) throws NotFoundException {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró el cliente con el ID: " + id));
        Optional<Cliente> clienteOpt = clienteRepository.findByNumeroIdentificacion(clienteRequest.getNumeroIdentificacion());

        if (clienteOpt.isPresent()) {
            throw new ConflicException("Ya existe un cliente registrado con el número de identificación: " + clienteRequest.getNumeroIdentificacion());
        }
            cliente.setTipoIdentificacion(clienteRequest.getTipoIdentificacion());
            cliente.setNumeroIdentificacion(clienteRequest.getNumeroIdentificacion());
            cliente.setNombres(clienteRequest.getNombres());
            cliente.setCorreo(clienteRequest.getCorreo());
            cliente.setTelefono(clienteRequest.getTelefono());

            Cliente clienteActualizado = clienteRepository.save(cliente);
            return conversionADto(clienteActualizado);
    }

    @Override
    public void eliminarClientePorId(Integer id) throws NotFoundException {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);

        if (clienteOpt.isPresent()) {
            clienteRepository.deleteById(id);
        } else {
            throw new NotFoundException("No se encontró el cliente con el ID: " + id);
        }
    }

    // Conversion de Modelo a Dto
    private ClienteDto conversionADto(Cliente cliente) {
        List<SucursalDto> sucursales = cliente.getSucursales().stream()
                .map(sucursal -> new SucursalDto(
                        sucursal.getId(),
                        sucursal.getProvincia(),
                        sucursal.getCiudad(),
                        sucursal.getDireccion(),
                        sucursal.getMatriz()
                ))
                .collect(Collectors.toList());

        return new ClienteDto(
                cliente.getId(),
                cliente.getTipoIdentificacion(),
                cliente.getNumeroIdentificacion(),
                cliente.getNombres(),
                cliente.getCorreo(),
                cliente.getTelefono(),
                sucursales
        );
    }
}
