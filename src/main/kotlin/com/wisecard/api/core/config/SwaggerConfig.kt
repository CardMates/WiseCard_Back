package com.wisecard.api.core.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.servers.Server
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfig {

    @Bean
    fun springOpenApi(): OpenAPI {
        return OpenAPI()
            .addServersItem(Server().url("/"))
    }
}