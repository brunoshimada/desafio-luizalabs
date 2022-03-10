package com.shimada.luizalabs.digitalmaps.config.swagger;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiInfoImpl {

    @Bean
    public GroupedOpenApi usersGroup(@Value("${info.springdocs.api.version}") String apiDocVersion) {
        return GroupedOpenApi.builder()
            .group("digitalMaps")
            .addOpenApiCustomiser(openApi -> openApi.info(new Info()
                                                              .title("Digital Maps API")
                                                              .version(apiDocVersion)))
            .build();
    }
}
