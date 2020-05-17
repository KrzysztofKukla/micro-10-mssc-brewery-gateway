package pl.kukla.krzys.micro10msscbrewerygateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Krzysztof Kukla
 */
@Profile("google")
@Configuration
public class GoogleConfig {

    @Bean
    public RouteLocator googleRouteConfig(RouteLocatorBuilder builder) {
        return builder.routes()
            //given rout we be redirected to 'uri'
            .route(r -> r.path("/googlesearch2")
                .filters(f -> f.rewritePath("/googlesearch2(?<segment>/?.*)", "/${segment}"))
                .uri("http://google.com")
                .id("google"))
            .build();
    }

}
