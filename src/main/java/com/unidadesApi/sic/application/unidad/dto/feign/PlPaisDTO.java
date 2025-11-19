package com.unidadesApi.sic.application.unidad.dto.feign;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class PlPaisDTO {

    private Long idPais;
    private String nombrePais;
    private String descripcionPais;

    private String numeroIndicativo;
    private BigDecimal estado;

    // Lista de departamentos
    private List<PlDepartamentoDTO> departamentos; // Asegúrate de tener este campo
}
