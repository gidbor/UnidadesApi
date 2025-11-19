package com.unidadesApi.sic.application.unidad.dto.feign;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlTipoUnidadDTO {

    private Long idTipoUnidad;
    private String nombreTipoUnidad;
    private String descripcionTipoUnidad;

    private BigDecimal estado;

}