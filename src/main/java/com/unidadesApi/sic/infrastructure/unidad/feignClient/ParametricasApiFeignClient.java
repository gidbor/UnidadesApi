package com.unidadesApi.sic.infrastructure.unidad.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.unidadesApi.sic.application.unidad.dto.feign.PlPaisDTO;
import com.unidadesApi.sic.application.unidad.dto.feign.PlDepartamentoDTO;
import com.unidadesApi.sic.application.unidad.dto.feign.PlCiudadDTO;
import com.unidadesApi.sic.application.unidad.dto.feign.PlTipoUnidadDTO;
import com.unidadesApi.sic.config.FeignConfig;

/**
 * Cliente Feign hacia el microservicio de Paramétricas.
 * 
 * Usamos los mismos paths que expone parametricas:
 *  - /api/plPaiss
 *  - /api/plDepartamentos
 *  - /api/plCiudads
 *  - /api/plTipoUnidades
 */
@FeignClient(
    name = "parametricasApi",
    url = "${url.service.client.path.parametricas}",
    configuration = FeignConfig.class
)
public interface ParametricasApiFeignClient {

    // ========= PAISES =========

    @GetMapping("/api/plPaiss")
    List<PlPaisDTO> getPaises();

    @GetMapping("/api/plPaiss/{idPais}")
    PlPaisDTO getPais(@PathVariable("idPais") Long idPais);


    // ========= DEPARTAMENTOS =========

    @GetMapping("/api/plDepartamentos")
    List<PlDepartamentoDTO> getDepartamentos();

    @GetMapping("/api/plDepartamentos/{idDepartamento}")
    PlDepartamentoDTO getDepartamento(@PathVariable("idDepartamento") Long idDepartamento);


    // ========= CIUDADES =========

    @GetMapping("/api/plCiudads")
    List<PlCiudadDTO> getCiudades();

    @GetMapping("/api/plCiudads/{idCiudad}")
    PlCiudadDTO getCiudad(@PathVariable("idCiudad") Long idCiudad);


    // ========= TIPOS DE UNIDAD =========

    @GetMapping("/api/plTipoUnidades")
    List<PlTipoUnidadDTO> getTiposUnidad();

    @GetMapping("/api/plTipoUnidades/{idTipoUnidad}")
    PlTipoUnidadDTO getTipoUnidad(@PathVariable("idTipoUnidad") Long idTipoUnidad);

}
