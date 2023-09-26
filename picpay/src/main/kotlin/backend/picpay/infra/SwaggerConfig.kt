package backend.picpay.infra

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition
class SwaggerConfig {
    @Bean
    fun api(): OpenAPI? {
        return OpenAPI().components(Components())
            .info(
                Info().title("API PICPAY").version("1.0.0")
                    .description("REST API documentation")
            )
    }
}