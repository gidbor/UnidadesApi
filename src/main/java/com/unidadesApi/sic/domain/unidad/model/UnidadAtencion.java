package com.unidadesApi.sic.domain.unidad.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "PL_UNIDAD_ATENCION")
@Data
@Builder
public class UnidadAtencion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "primary_sequence")
    private Long idUnidadAtencion;

    // ===== CAMPOS ORIGINALES DE PL_UNIDAD_ATENCION =====

    @Column(nullable = false, length = 100)
    private String nombreUnidadAtencion;

    @Column
    private String descripcionUnidadAtencion;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal estado;

    @Column(name = "ID_TIPO_UNIDAD", nullable = false)
    private Long idTipoUnidad;

    // ===== NUEVOS CAMPOS DEL ALTER TABLE =====

    @Column(name = "ID_COORDINADOR", length = 255)
    private String idCoordinador;            // userId de Keycloak

    @Column(name = "NOMBRE_COORDINADOR", length = 150)
    private String nombreCoordinador;        // snapshot legible del nombre

    @Column(name = "DIRECCION", length = 255)
    private String direccion;

    @Column(name = "CORREO_ELECTRONICO", length = 255)
    private String correoElectronico;

    @Column(name = "ID_PAIS")
    private Long idPais;

    @Column(name = "ID_DEPARTAMENTO")
    private Long idDepartamento;

    @Column(name = "ID_CIUDAD")
    private Long idCiudad;

    @Column(name = "ID_LOCALIDAD")
    private Long idLocalidad;

    @Column(name = "TELEFONO", length = 50)
    private String telefono;

    @Column(name = "FECHA_CREACION", nullable = false)
    private OffsetDateTime fechaCreacion;

}
