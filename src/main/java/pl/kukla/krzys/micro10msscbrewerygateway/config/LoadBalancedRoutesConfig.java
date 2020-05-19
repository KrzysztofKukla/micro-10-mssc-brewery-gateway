package pl.kukla.krzys.micro10msscbrewerygateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Krzysztof Kukla
 */
@Profile(value = "local-discovery")
@Configuration
public class LoadBalancedRoutesConfig {

    //Load Balance to use many instances of microservices
    @Bean
    public RouteLocator loadBalancedRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            //here we route/redirect paths into 'localhost:8080'
            .route(r -> r.path("/api/v1/beer*", "/api/v1/beer/*", "/api/v1/beerUpc/*")
                //redirect to beerService which is running on 'localhost:8080'
                //lb - for load Balance
                .uri("lb://micro-02-mssc-beer-service")
                .id("beer-service"))
            .route(r -> r.path("/api/v1/customers/**")
                .uri("lb://micro-05-mssc-beer-order-service")
                .id("beer-order-service"))
            .route(r -> r.path("/api/v1/beer/*/inventory")
                .uri("lb://micro-06-mssc-beer-inventory-service")
                .id("beer-inventory-service"))
            .build();
    }

}
