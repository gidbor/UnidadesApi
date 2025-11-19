package com.unidadesApi.sic.application.unidad.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class CrearUnidadRequest {

    @NotBlank
    @Size(max = 100)
    private String nombreUnidadAtencion;

    @Size(max = 255)
    private String descripcionUnidadAtencion;

    // ===== CATÁLOGOS (paramétricas) =====

    @NotNull
    private Long idTipoUnidad;

    @NotNull
    private Long idPais;

    @NotNull
    private Long idDepartamento;

    @NotNull
    private Long idCiudad;

    // No existe tabla de localidades hoy, lo dejamos opcional
    private Long idLocalidad;

    // ===== DATOS DE CONTACTO / UBICACIÓN =====

    @NotBlank
    @Size(max = 255)
    private String direccion;

    @Email
    @Size(max = 255)
    private String correoElectronico;

    @Size(max = 50)
    private String telefono;

    // ===== COORDINADOR (Keycloak) =====

    // TODO: Pendiente configuración Keyloak

    /**
     * userId del coordinador en Keycloak.
     * Más adelante este mismo tipo lo podemos reutilizar para otros usuarios (recorridos, etc.)
     */
    @NotBlank
    @Size(max = 255)
    private String idCoordinador;


    @Size(max = 255)
    private String nombreCoordinador;


    // Por ahora NO exponemos nombreCoordinador en el request:
    // lo resolveremos desde el SSO de Keycloak (y lo guardamos en la entidad).
}
