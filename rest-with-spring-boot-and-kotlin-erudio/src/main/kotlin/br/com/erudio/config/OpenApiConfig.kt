package br.com.erudio.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenApi(): OpenAPI{

        return OpenAPI()
            .info(
                Info()
                    .title("RESTfull API with kotlin 1.9.10 and SpringBoot 3.2.3")
                    .version("v1")
                    .description("Sample API example in kotlin")
                    .termsOfService("https://github.com/Edwin160508/rest-with-spring-boot-and-kotlin-erudio")
                    .license(
                        License().name("Apache 2.0")
                            .url("https://github.com/Edwin160508/rest-with-spring-boot-and-kotlin-erudio/blob/main/LICENSE")
                    )
            )
    }

}