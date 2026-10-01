package com.HaydiKodlayalim;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Pet API Dokümantasyonu")
                        .version("1.0.0")
                        .description("Spring Boot Swagger / OpenAPI 3 Dokümantasyon Örneği")
                        .contact(new Contact()
                                .name("Haydi Kodlayalim")
                                .url("https://github.com/haydikodlayalim"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org")));
    }
}
