package tn.esprit.tpcafeziedsnoussi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TPCafe API")
                        .description("TPCafe Spring Boot REST API Documentation")
                        .version("1.0"));
    }
}
