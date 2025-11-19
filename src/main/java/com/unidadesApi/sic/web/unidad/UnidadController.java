package com.unidadesApi.sic.web.unidad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unidadesApi.sic.application.unidad.dto.request.CrearUnidadRequest;
import com.unidadesApi.sic.application.unidad.dto.response.UnidadResponse;
import com.unidadesApi.sic.application.unidad.usecase.CrearUnidadUseCase;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/unidades")
public class UnidadController {

    @Autowired
    private CrearUnidadUseCase crearUnidadUseCase;

    @Operation(
        summary = "Crear unidad de atención",
        description = "Crea una nueva unidad de atención con los datos de tipo de unidad, ubicación y coordinador."
    )
    @PostMapping(value = "/guardar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UnidadResponse> crearUnidad(@Valid @RequestBody CrearUnidadRequest request) {

        UnidadResponse response = crearUnidadUseCase.ejecutar(request);
        // 201 Created porque es un recurso nuevo
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
