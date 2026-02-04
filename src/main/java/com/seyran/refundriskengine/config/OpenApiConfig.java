package com.seyran.refundriskengine.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI refundRiskOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Refund Risk API")
                .description("Rule-based engine to calculate refund risk score")
                .version("1.0.0"));
    }
}
