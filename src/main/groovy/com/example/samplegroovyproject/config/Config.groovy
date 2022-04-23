package com.example.samplegroovyproject.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr353.JSR353Module
import org.springframework.boot.actuate.endpoint.SanitizableData
import org.springframework.boot.actuate.endpoint.SanitizingFunction
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.CommandLinePropertySource


/**
 * @author cdaii* @created 23/04/2022 - 4:45 PM
 * @project sample-groovy-project
 * */

@Configuration
class Config {

    @Bean
    SanitizingFunction sanitizingFunction(){
        return new SanitizingFunction() {
            @Override
            SanitizableData apply(SanitizableData data) {
                return data.getPropertySource().getName() == CommandLinePropertySource.COMMAND_LINE_PROPERTY_SOURCE_NAME ?
                        data.withValue("not gonna get it") : data
            }
        }
    }

    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JSR353Module());

        return objectMapper
    }
}
