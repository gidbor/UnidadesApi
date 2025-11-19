package com.unidadesApi.sic.application.unidad.mapper;

import org.springframework.stereotype.Service;

import com.unidadesApi.sic.application.mapper.TMapper;
import com.unidadesApi.sic.application.unidad.dto.response.UnidadResponse;
import com.unidadesApi.sic.domain.unidad.model.UnidadAtencion;

@Service
public class UnidadAtencionMapper extends TMapper<UnidadAtencion, UnidadResponse> {

    public UnidadAtencionMapper() {
        super.setClassTypes(UnidadAtencion.class, UnidadResponse.class);
    }

}
