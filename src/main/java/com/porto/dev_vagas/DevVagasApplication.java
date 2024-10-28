package com.porto.dev_vagas;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Dev Vagas",
				description = "API para gestão de vagas",
				version = "1.0"
		))
@SecurityScheme(name = "jwt_auth", scheme = "bearer",
		bearerFormat = "JWT", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class DevVagasApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevVagasApplication.class, args);
	}

}
