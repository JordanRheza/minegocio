package com.alquimiasoft.minegocio.serviceTest;

import com.alquimiasoft.minegocio.dto.ClienteDto;
import com.alquimiasoft.minegocio.dto.SucursalDto;
import com.alquimiasoft.minegocio.exception.ConflicException;
import com.alquimiasoft.minegocio.exception.NotFoundException;
import com.alquimiasoft.minegocio.model.Cliente;
import com.alquimiasoft.minegocio.repository.ClienteRepository;
import com.alquimiasoft.minegocio.service.ClienteService;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTests {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private ClienteDto clienteDto;
    private Cliente cliente;

    @BeforeEach
    void setup() {

        clienteDto = new ClienteDto();
        clienteDto.setId(2);
        clienteDto.setTipoIdentificacion("RUC");
        clienteDto.setNumeroIdentificacion("0987654321");
        clienteDto.setNombres("Jordan Rheza");
        clienteDto.setCorreo("rheza@gmail.com");
        clienteDto.setTelefono("123456789");

        SucursalDto sucursalDto = new SucursalDto();
        sucursalDto.setProvincia("Guayas");
        sucursalDto.setCiudad("Guayaquil");
        sucursalDto.setMatriz(true);
        sucursalDto.setDireccion("Calle 1");

        clienteDto.setSucursales(List.of(sucursalDto));
    }

    @DisplayName("Test para guardar un cliente")
    @Test
    void testGuardarCliente() {
        given(clienteRepository.findByNumeroIdentificacion(clienteDto.getNumeroIdentificacion()))
                .willReturn(Optional.empty());

        given(clienteRepository.save(any(Cliente.class))).willAnswer(invocation -> {
            Cliente cliente = invocation.getArgument(0);
            cliente.setId(2);
            return cliente;
        });

        ClienteDto clienteGuardado = clienteService.guardarCliente(clienteDto);

        Assertions.assertThat(clienteGuardado).isNotNull();
        Assertions.assertThat(clienteGuardado.getSucursales()).hasSize(1);

        Assertions.assertThat(clienteGuardado.getSucursales().get(0).getProvincia()).isEqualTo("Guayas");
        Assertions.assertThat(clienteGuardado.getSucursales().get(0).getMatriz()).isTrue();
    }

    @DisplayName("Test para guardar un cliente existente")
    @Test
    void testGuardarClienteExistente() {
        Cliente clienteExistente = new Cliente();
        clienteExistente.setId(1);
        clienteExistente.setNumeroIdentificacion(clienteDto.getNumeroIdentificacion());

        given(clienteRepository.findByNumeroIdentificacion(clienteDto.getNumeroIdentificacion()))
                .willReturn(Optional.of(clienteExistente));

        assertThrows(ConflicException.class, () -> clienteService.guardarCliente(clienteDto));

        verify(clienteRepository, never()).save(any(Cliente.class));
    }

}
