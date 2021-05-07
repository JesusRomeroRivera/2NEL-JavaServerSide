package com.minka.tunel.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean(name = "tunelOpenApi" )
    public OpenAPI tunelOpenApi() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                    .title("Tunel Application API")
                    .description("Tunel API implementation"));
    }
}
