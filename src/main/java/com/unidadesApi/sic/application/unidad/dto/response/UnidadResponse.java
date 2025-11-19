package com.unidadesApi.sic.application.unidad.dto.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class UnidadResponse {

    private Long idUnidadAtencion;

    private String nombreUnidadAtencion;
    private String descripcionUnidadAtencion;

    private BigDecimal estado;
    private Long idTipoUnidad;

    private String idCoordinador;
    private String nombreCoordinador;

    private String direccion;
    private String correoElectronico;
    private Long idPais;
    private Long idDepartamento;
    private Long idCiudad;
    private Long idLocalidad;
    private String telefono;

    private OffsetDateTime fechaCreacion;

}
