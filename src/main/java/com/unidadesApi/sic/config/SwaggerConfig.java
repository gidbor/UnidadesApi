package com.unidadesApi.sic.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition(
        info = @Info(title = "API RNPC Unidades", version = "v1"),
        security = @SecurityRequirement(name = "bearerAuth")
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {

    @Bean
    public OpenAPI openApiSpec() {
        return new OpenAPI().components(new Components()
                .addSchemas("ApiErrorResponse", new ObjectSchema()
                        .addProperty("status", new IntegerSchema())
                        .addProperty("code", new StringSchema())
                        .addProperty("message", new StringSchema())
                        .addProperty("fieldErrors", new ArraySchema().items(
                                new Schema<ArraySchema>().$ref("ApiFieldError"))))
                .addSchemas("ApiFieldError", new ObjectSchema()
                        .addProperty("code", new StringSchema())
                        .addProperty("message", new StringSchema())
                        .addProperty("property", new StringSchema())
                        .addProperty("rejectedValue", new ObjectSchema())
                        .addProperty("path", new StringSchema())));
    }

    @Bean
    public OperationCustomizer operationCustomizer() {
        // add error type to each operation
        return (operation, handlerMethod) -> {
            operation.getResponses().addApiResponse("4xx/5xx", new ApiResponse()
                    .description("Error")
                    .content(new Content().addMediaType("*/*", new MediaType().schema(
                            new Schema<MediaType>().$ref("ApiErrorResponse")))));
            return operation;
        };
    }

    @Bean
    public GroupedOpenApi actuatorApi() {
        return GroupedOpenApi.builder()
                .group("Actuator")
                .pathsToMatch("/actuator/**")
                .build();
    }

    @Bean
    public GroupedOpenApi apiApi() {
        return GroupedOpenApi.builder()
                .group("Unidades")
                .pathsToMatch("/api/**")
                .build();
    }

}
