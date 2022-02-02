package com.example.samplegroovyproject

import org.springframework.boot.SpringApplication
import org.springframework.boot.actuate.endpoint.SanitizableData
import org.springframework.boot.actuate.endpoint.SanitizingFunction
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.env.CommandLinePropertySource

@SpringBootApplication
class SampleGroovyProjectApplication {

	@Bean
	SanitizingFunction sanitizingFunction(){
		return new SanitizingFunction() {
			@Override
			SanitizableData apply(SanitizableData data) {
				return data.getPropertySource().getName() == CommandLinePropertySource.COMMAND_LINE_PROPERTY_SOURCE_NAME ?
						data.withValue("not gonna get it") : data;
			}
		}
	}
	static void main(String[] args) {
		SpringApplication.run SampleGroovyProjectApplication, args
	}
}
