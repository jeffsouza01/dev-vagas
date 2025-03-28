package com.porto.dev_vagas.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Dev Vagas API")
                        .description("Spring API - Dev Vagas - Gerenciamento de vagas")
                        .version("v0.0.1")
                        .contact(new Contact().name("Jeff").url("https://github.com/jeffsouza01").email("jeffersonsilva.souza@portoseguro.com.br"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"))
                .schemaRequirement("jwt-auth", createSecurityScheme());

    }

    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme().name("jwt-auth").type(SecurityScheme.Type.HTTP)
                .scheme("bearer").bearerFormat("JWT");
    }

}
