package com.it.reservation.configure;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.SpecVersion;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

    // @Value("${server.port}")
    // private String serverPortx;
    @Value("${server.servlet.context-path}")
    private String serverPath;

    @Bean
    OpenAPI customOpenAPI() {
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("Bearer")
                .addList(String.valueOf(new Scopes().addString("global", "access all APIs")));
        return new OpenAPI(SpecVersion.V30).info(apiInfo()).externalDocs(new ExternalDocumentation()).servers(servers())
                .security(Collections.singletonList(securityRequirement))
                .components(new Components().addSecuritySchemes("Bearer",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }

    private Info apiInfo() {
        Info info = new Info();
        info.title("Swagger Documentation");
        info.description("This project is developed to explore about spring doc configurations");
        return info;
    }

    private List<Server> servers() {
        Server server = new Server();
        // you can add your production url as host
        server.url(serverPath);
        server.description("API URL");
        return List.of(server);
    }
}
