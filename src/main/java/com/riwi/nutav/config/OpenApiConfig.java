package com.riwi.nutav.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
//Para configurar beans dentro del spring
@Configuration
@OpenAPIDefinition(
    info= @Info(
        title= "Api para administracion de bd Nutav",
        version = "1.0",
        description = "Esta api es creada para el uso de guias, tour, clientes y reservaciones de la empresa Nutav "
    )
)
public class OpenApiConfig {
    
}
