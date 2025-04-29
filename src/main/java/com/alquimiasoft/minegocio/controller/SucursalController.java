package com.alquimiasoft.minegocio.controller;

import com.alquimiasoft.minegocio.dto.SucursalDto;
import com.alquimiasoft.minegocio.exception.NotFoundException;
import com.alquimiasoft.minegocio.service.SucursalService;
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
@Tag(name = "Controlador sucursal.", description = "Pemite agregar y listar las sucursales de un cliente")
public class SucursalController {
    @Autowired
    private SucursalService sucursalService;

    // Funcionalidad para registrar una nueva dirección por cliente
    @PostMapping("surcusal/{id}")
    @Operation(summary = "Agregar un nueva sucursal al cliente.", description = "Agrega una sucursal al cliente")
    public ResponseEntity<Map<String, String>> agregarCliente(@Valid @PathVariable int id, @RequestBody SucursalDto sucursalDto) throws NotFoundException {
        this.sucursalService.agregarSucursal(id, sucursalDto);

        Map<String, String> response = new HashMap<>();
        response.put("mensajeSistema", "Created");
        response.put("mensajeUsuario", "Sucursal agregada al cliente");
        response.put("estado", "201");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Funcionalidad para listar las direcciones adicionales del cliente
    @GetMapping("/sucursal/{id}")
    @Operation(summary = "Obtener un listado de sucursales de un cliente.", description = "Obtiene un listado de sucursales de un cliente.")
    public  ResponseEntity<List<SucursalDto>> listarSucursalesClientes (@PathVariable int id) throws NotFoundException {

        List<SucursalDto> sucursalesCliente = sucursalService.listarDirecciones(id);

        return ResponseEntity.ok(sucursalesCliente);
    }
}
