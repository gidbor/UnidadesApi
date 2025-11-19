package com.unidadesApi.sic.application.unidad.dto.feign;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

import lombok.Data;


@Data
public class PlDepartamentoDTO {

    private Long idDepartamento;

    @NotNull
    @Size(max = 100)
    private String nombreDepartamento;

    @Size(max = 255)
    private String descripcionDepartamento;

    @Size(max = 10)
    private String codigoDivipola;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(type = "string", example = "42.08")
    private BigDecimal estado;

    private Long pais;

    private List<PlCiudadDTO> ciudades;
}
