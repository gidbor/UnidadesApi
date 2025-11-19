package com.unidadesApi.sic.infrastructure.unidad.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.unidadesApi.sic.domain.unidad.model.UnidadAtencion;

/**
 * findByIdUnidadAtencion nos servirá en el use case de consultar/actualizar/eliminar.
 * findByEstado te permite filtrar solo activas (si usas estado = 1, por ejemplo).
 * findByIdTipoUnidadOptional es útil para listados filtrando por tipo; si se envia null, devuelve todas.
 */

public interface UnidadAtencionRepository extends JpaRepository<UnidadAtencion, Long> {

    @Query(value = "SELECT u FROM UnidadAtencion u WHERE u.idUnidadAtencion = :idUnidadAtencion",
           nativeQuery = false)
    List<UnidadAtencion> findByIdUnidadAtencion(@Param("idUnidadAtencion") Long idUnidadAtencion);

    @Query(value = "SELECT u FROM UnidadAtencion u WHERE u.estado = :estado",
           nativeQuery = false)
    List<UnidadAtencion> findByEstado(@Param("estado") BigDecimal estado);

    @Query(value = "SELECT u FROM UnidadAtencion u " +
                   "WHERE (:idTipoUnidad IS NULL OR u.idTipoUnidad = :idTipoUnidad)",
           nativeQuery = false)
    List<UnidadAtencion> findByIdTipoUnidadOptional(@Param("idTipoUnidad") Long idTipoUnidad);

}