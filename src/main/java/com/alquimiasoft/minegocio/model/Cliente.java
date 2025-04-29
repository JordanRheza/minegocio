package com.alquimiasoft.minegocio.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 30)
    private String tipoIdentificacion;

    @Column(nullable = false, length = 12)
    private String numeroIdentificacion;

    @Column(nullable = false, length = 100)
    private String nombres;

    @Column(nullable = false, length = 100)
    private String correo;

    @Column(length = 10)
    private String telefono;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Sucursal> sucursales;
}
