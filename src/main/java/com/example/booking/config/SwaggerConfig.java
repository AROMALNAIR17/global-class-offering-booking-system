package com.example.booking.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI bookingSystemOpenAPI() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("Global Class Offering Booking System API")
                                .description("Backend service for managing course offerings, sessions and bookings")
                                .version("1.0")
                                .contact(
                                        new Contact()
                                                .name("Developer")
                                                .email("developer@example.com")
                                )
                );
    }
}