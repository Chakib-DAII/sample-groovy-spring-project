package com.example.samplegroovyproject.config.swagger

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springdoc.core.GroupedOpenApi
import org.springdoc.core.customizers.OpenApiCustomiser
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


/**
 * @author cdaii* @created 23/04/2022 - 5:11 PM
 * @project sample-groovy-project
 * */

@Configuration
@OpenAPIDefinition(info = @Info(title = "Swagger Demo", version = "1.0", description = "Documentation APIs v1.0"))
class SwaggerConfig {

    @Bean
    GroupedOpenApi employeeGroupApi() {
        return GroupedOpenApi.builder()
                .group("Customers")
                .pathsToMatch("/customers/**")
                //.addOpenApiCustomiser(getOpenApiCustomiser())
                .build();
    }


    /*OpenApiCustomiser getOpenApiCustomiser() {

        return (openAPI) -> openAPI.getPaths().values().stream().flatMap(pathItem ->
                pathItem.readOperations().stream())
                .forEach(operation -> {
                    operation.addParametersItem(new Parameter().name("Authorization").in("header").
                            schema(new StringSchema().example("token")).required(true))
                    operation.addParametersItem(new Parameter().name("userId").in("header").
                            schema(new StringSchema().example("test")).required(true))

                });
    }*/
}
