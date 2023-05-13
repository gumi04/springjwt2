package com.example.demogradle.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration

public class SwaggerConfig {

  /**
   * Configuracion de Swagger.
   *
   * @return Constructor de la interfaz primaria SWAGGER.
   */
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage("com.example.demogradle.web.controller"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo());
  }

  /**
   * Configuracion de Swagger.
   *
   * @return metadata
   */
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("API de ejemplo")
        .description("Documentación de la API de ejemplo")
        .version("1.0")
        .build();
  }

}
