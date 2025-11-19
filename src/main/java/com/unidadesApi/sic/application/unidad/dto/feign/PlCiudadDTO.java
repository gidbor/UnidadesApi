package com.unidadesApi.sic.application.unidad.dto.feign;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class PlCiudadDTO {

    private Long idCiudad;

    @NotNull
    @Size(max = 100)
    private String nombreCiudad;

    @Size(max = 255)
    private String descripcionCiudad;

    @Size(max = 10)
    private String codigoDivipola;

    @Size(max = 10)
    private String codigoDivipolaDepartamento;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(type = "string", example = "42.08")
    private BigDecimal estado;

    private Long departamento;

}
