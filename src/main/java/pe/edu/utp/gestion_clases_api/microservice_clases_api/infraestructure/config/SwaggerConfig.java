package pe.edu.utp.gestion_clases_api.microservice_clases_api.infraestructure.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.*;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Académica UTP")
                        .version("1.0")
                        .description("Documentación de los endpoints del microservicio académico"));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("academico")
                .pathsToMatch("/api/**")
                .build();
    }
}