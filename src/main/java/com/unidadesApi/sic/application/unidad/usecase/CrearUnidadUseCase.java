package com.unidadesApi.sic.application.unidad.usecase;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unidadesApi.sic.application.unidad.dto.feign.PlCiudadDTO;
import com.unidadesApi.sic.application.unidad.dto.feign.PlDepartamentoDTO;
import com.unidadesApi.sic.application.unidad.dto.feign.PlPaisDTO;
import com.unidadesApi.sic.application.unidad.dto.feign.PlTipoUnidadDTO;
import com.unidadesApi.sic.application.unidad.dto.request.CrearUnidadRequest;
import com.unidadesApi.sic.application.unidad.dto.response.UnidadResponse;
import com.unidadesApi.sic.application.unidad.mapper.UnidadAtencionMapper;
import com.unidadesApi.sic.domain.unidad.model.UnidadAtencion;
import com.unidadesApi.sic.infrastructure.unidad.feignClient.ParametricasApiFeignClient;
import com.unidadesApi.sic.infrastructure.unidad.repository.UnidadAtencionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CrearUnidadUseCase {

    @Autowired
    private final UnidadAtencionRepository unidadAtencionRepository;

    @Autowired
    private final UnidadAtencionMapper unidadAtencionMapper;

    @Autowired
    private final ParametricasApiFeignClient parametricasApiFeignClient;

    @Transactional
    public UnidadResponse ejecutar(CrearUnidadRequest request) {

        // ===== 1. Validaciones/consultas a catálogos (paramétricas) =====
        PlTipoUnidadDTO tipoUnidad = parametricasApiFeignClient.getTipoUnidad(request.getIdTipoUnidad());
        PlPaisDTO pais = parametricasApiFeignClient.getPais(request.getIdPais());
        PlDepartamentoDTO departamento = parametricasApiFeignClient.getDepartamento(request.getIdDepartamento());
        PlCiudadDTO ciudad = parametricasApiFeignClient.getCiudad(request.getIdCiudad());

        // Aquí podríamos hacer validaciones adicionales de negocio:
        // - validar que estado == 1 (activo)
        // - validar que ciudad pertenece al departamento, etc.
        // De momento asumimos que si Feign no lanza excepción, el dato es válido.

        // ===== 2. Construcción de la entidad =====
        UnidadAtencion unidad = new UnidadAtencion();

        unidad.setNombreUnidadAtencion(request.getNombreUnidadAtencion());
        unidad.setDescripcionUnidadAtencion(request.getDescripcionUnidadAtencion());

        unidad.setIdTipoUnidad(tipoUnidad.getIdTipoUnidad());
        unidad.setIdPais(pais.getIdPais());
        unidad.setIdDepartamento(departamento.getIdDepartamento());
        unidad.setIdCiudad(ciudad.getIdCiudad());
        unidad.setIdLocalidad(request.getIdLocalidad()); // por ahora opcional

        unidad.setDireccion(request.getDireccion());
        unidad.setCorreoElectronico(request.getCorreoElectronico());
        unidad.setTelefono(request.getTelefono());

        unidad.setIdCoordinador(request.getIdCoordinador());
        unidad.setNombreCoordinador(request.getNombreCoordinador());

        // Estado por defecto: ACTIVO (1)
        unidad.setEstado(BigDecimal.ONE);

        // Fecha de creación en el backend
        unidad.setFechaCreacion(OffsetDateTime.now());

        // ===== 3. Persistencia =====
        UnidadAtencion unidadGuardada = unidadAtencionRepository.save(unidad);

        // ===== 4. Mapeo a respuesta =====
        return unidadAtencionMapper.toDto(unidadGuardada);
    }

}
