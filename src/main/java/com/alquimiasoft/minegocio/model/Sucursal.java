package com.alquimiasoft.minegocio.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @Column(nullable = false, length = 100)
    private String provincia;

    @Column(nullable = false, length = 100)
    private String ciudad;

    @Column(length = 100)
    private String direccion;

    @Column(nullable = false)
    private Boolean matriz;

    @ManyToOne
    @JsonBackReference
    @Schema(hidden = true)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
