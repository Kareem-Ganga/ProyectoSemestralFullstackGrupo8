package com.example.ProyectoSemestralFullstackGrupo8.Config;


import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Fullstack Grupo 8")
                        .version("0.5")
                        .description("Documentacion de la API para la aplicación"));
    }

}
