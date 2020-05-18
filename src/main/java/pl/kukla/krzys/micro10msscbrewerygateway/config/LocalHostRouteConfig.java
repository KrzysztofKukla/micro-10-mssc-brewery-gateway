package pl.kukla.krzys.micro10msscbrewerygateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Krzysztof Kukla
 */
@Configuration
public class LocalHostRouteConfig {

    @Bean
    public RouteLocator beerServiceRoute(RouteLocatorBuilder builder) {
        return builder.routes()
            //here we route/redirect paths into 'localhost:8080'
            .route(r -> r.path("/api/v1/beer*", "/api/v1/beer/*", "/api/v1/beerUpc/*")
                //redirect to beerService which is running on 'localhost:8080'
                .uri("http://localhost:8080")
                .id("beer-service"))
            .route(r -> r.path("/api/v1/customers/**")
                .uri("http://localhost:8081")
                .id("beer-order-service"))
            .route(r -> r.path("/api/v1/beer/*/inventory")
                .uri("http://localhost:8082")
                .id("beer-inventory-service"))

            .build();
    }

}
