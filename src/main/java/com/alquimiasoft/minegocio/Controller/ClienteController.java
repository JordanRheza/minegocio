package com.alquimiasoft.minegocio.Controller;

import com.alquimiasoft.minegocio.Dto.ClienteDto;
import com.alquimiasoft.minegocio.Service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    // Funcionalidad para obtener un listado de clientes
    @GetMapping("/clientes")
    public List<ClienteDto> obtenerClientes() {
        return clienteService.listarClientes();
    }

    // Funcionalidad para buscar un clientes por nombre o Dni
    @GetMapping("/clientes/")
    public ResponseEntity<ClienteDto> obtenerClientePorDniONombres(
            @RequestParam String numeroIdentificacion,
            @RequestParam(required = false) String nombres) {
        ClienteDto clienteDto = clienteService.buscarCliente(numeroIdentificacion, nombres);
        return ResponseEntity.ok(clienteDto);
    }
}
