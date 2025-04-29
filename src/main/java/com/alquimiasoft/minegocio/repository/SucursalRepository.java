package com.alquimiasoft.minegocio.repository;

import com.alquimiasoft.minegocio.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {

    List<Sucursal> findByClienteId(Integer clienteId);
    boolean existsByClienteIdAndMatrizTrue(Integer clienteId);

}
