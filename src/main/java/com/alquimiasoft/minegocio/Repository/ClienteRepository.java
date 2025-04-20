package com.alquimiasoft.minegocio.Repository;

import com.alquimiasoft.minegocio.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByNumeroIdentificacionOrNombres(String numeroIdentificacion, String nombres);
}
