package com.weathermagagement.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
class SwaggerConfig {
    private val API_NAME = "AGILE_THUNDER_THEORY Project API"
    private val API_VERSION = "0.0.1"
    private val API_DESCRIPTION = "WEATHER_MANAGEMENT 프로젝트 명세서"

    @Bean
    fun api(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(this.apiInfo())
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()

    }

    fun apiInfo(): ApiInfo? {
        return ApiInfoBuilder()
            .title(this.API_NAME)
            .version(this.API_VERSION)
            .description(this.API_DESCRIPTION)
            .contact(Contact("sangsu", "", "ssk5442@naver.som"))
            .build()
    }
}