package br.com.jhowsoftware.resgateanimal.config;

import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import java.util.List;

@Configuration
public class OpenApiConfig
{
    @Bean
    public OpenAPI customOpenAPI()
    {
        return new OpenAPI()
                .info(new Info()
                        .title("Resgate Animal")
                        .version("v1")
                        .description("Documentação das APIs do Projeto Resgate Animal")
                )
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Servidor de Desenvolvimento")
                ));
    }
}

