package com.alquimiasoft.minegocio.controller;

import com.alquimiasoft.minegocio.dto.ClienteDto;
import com.alquimiasoft.minegocio.exception.NotFoundException;
import com.alquimiasoft.minegocio.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@Tag(name = "Controlador cliente.", description = "Permite ver, agregar, eliminar o editar los datos de un cliente.")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Funcionalidad para obtener un listado de clientes
    @GetMapping("/clientes")
    @Operation(summary = "Obtener los datos de los clientes.", description = "Obtiene los datos de todos los clientes registrados en la base de datos.")
    public List<ClienteDto> obtenerClientes() throws NotFoundException {
        return clienteService.listarClientes();
    }

    // Funcionalidad para buscar un clientes por nombre o Dni
    @GetMapping("/cliente/")
    @Operation(summary = "Obtener los datos de un cliente.", description = "Obtiene los datos de un cliente, con el parametro número de identificación o nombres.")
    public ResponseEntity<List<ClienteDto>> obtenerClientesPorDniONombres(
            @RequestParam(required = false) String numeroIdentificacion,
            @RequestParam(required = false) String nombres) throws NotFoundException {

        List<ClienteDto> clientes = clienteService.buscarClientes(numeroIdentificacion, nombres);
        return ResponseEntity.ok(clientes);
    }

    // Funcionalidad para crear un nuevo cliente con la dirección matriz y adicionales
    @PostMapping("/cliente")
    @Operation(summary = "Registrar un cliente.", description = "Registra los datos de un cliente y lo guarda en la base de datos.")
    public ResponseEntity<ClienteDto> agregarCliente(@Valid @RequestBody ClienteDto clienteDto) {

        ClienteDto clienteNuevo = clienteService.guardarCliente(clienteDto);

        return new ResponseEntity<>(clienteNuevo, HttpStatus.CREATED);
    }

    // Funcionalidad para editar los datos del cliente
    @PutMapping("/cliente/{id}")
    @Operation(summary = "Actualizar los datos de un cliente.", description = "Actualiza los datos de un cliente y lo guarda en la base de datos.")
    public  ResponseEntity<ClienteDto> actualizarClientePorId (@PathVariable int id, @Valid @RequestBody ClienteDto clienteRequest) throws NotFoundException {

        ClienteDto clienteActualizado = clienteService.actualizarClientePorId(id, clienteRequest);

        return ResponseEntity.ok(clienteActualizado);
    }

    // Funcionalidad para eliminar un cliente
    @DeleteMapping("/cliente/{id}")
    @Operation(summary = "Eliminar un cliente.", description = "Elimina un cliente.")
    public ResponseEntity<Map<String, String>> eliminarCliente(@PathVariable int id) throws NotFoundException {
        clienteService.eliminarClientePorId(id);

        Map<String, String> response = new HashMap<>();
        response.put("mensajeSistema", "OK");
        response.put("mensajeUsuario", "Cliente eliminado de la base de datos");
        response.put("estado", "200");

        return ResponseEntity.ok(response);
    }
}
