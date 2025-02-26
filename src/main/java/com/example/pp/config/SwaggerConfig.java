package com.example.pp.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class SwaggerConfig {

    @Bean
    public OpenAPI custonOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Client service API")
                        .version("1.0")
                        .description("API для работы с клиентами"));
    }
}
