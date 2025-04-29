package com.alquimiasoft.minegocio.service;

import com.alquimiasoft.minegocio.dto.SucursalDto;
import com.alquimiasoft.minegocio.exception.ConflicException;
import com.alquimiasoft.minegocio.exception.NotFoundException;
import com.alquimiasoft.minegocio.model.Cliente;
import com.alquimiasoft.minegocio.model.Sucursal;
import com.alquimiasoft.minegocio.repository.ClienteRepository;
import com.alquimiasoft.minegocio.repository.SucursalRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SucursalService implements ISucursalService{

    private static final Logger log = LogManager.getLogger(SucursalService.class);
    @Autowired
    SucursalRepository sucursalRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public void agregarSucursal(Integer clienteId, SucursalDto nuevaSurcusal) throws NotFoundException {

        Optional<Cliente> clienteOpt = clienteRepository.findById(clienteId);

        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            if (Boolean.TRUE.equals(nuevaSurcusal.getMatriz()) &&
                    sucursalRepository.existsByClienteIdAndMatrizTrue(clienteId)) {
                throw new ConflicException("El cliente ya tiene una dirección matriz asignada.");
            }
            Sucursal sucursal = new Sucursal();
            sucursal.setProvincia(nuevaSurcusal.getProvincia());
            sucursal.setCiudad(nuevaSurcusal.getCiudad());
            sucursal.setDireccion(nuevaSurcusal.getDireccion());
            sucursal.setMatriz(false);
            sucursal.setCliente(cliente);
            sucursalRepository.save(sucursal);

        } else {
            throw new NotFoundException("No se encontró el cliente con el ID: " + clienteId);
        }
    }

    @Override
    public List<SucursalDto> listarDirecciones(Integer clienteId) {

        List<SucursalDto> datosClientes;

        datosClientes = this.sucursalRepository.findByClienteId(clienteId).stream()
                .map((this::conversionADto))
                .toList();
        return datosClientes;
    }

    // Conversion de Modelo a Dto
    private SucursalDto conversionADto(Sucursal sucursal) {
        return new SucursalDto(
                sucursal.getId(),
                sucursal.getProvincia(),
                sucursal.getCiudad(),
                sucursal.getDireccion(),
                sucursal.getMatriz()
        );
    }
}
