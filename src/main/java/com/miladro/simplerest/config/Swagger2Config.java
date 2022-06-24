//package com.miladro.simplerest.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.bind.annotation.RestController;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//
//import java.util.List;
//
//import static springfox.documentation.builders.PathSelectors.regex;
//
//@Configuration
//public class Swagger2Config {
//
//    private static final String AUTHORIZATION_HEADER = "Authorization";
//    private static final String DEFAULT_INCLUDE_PATTERN = "/api/**";
//
//    @Bean
//    public Docket apiAndPublicPathPrefixDocketBean() {
//        return new Docket(DocumentationType.SWAGGER_2).groupName("apiAndPublicPathPrefix").select()
//            .apis(RequestHandlerSelectors
//                .withClassAnnotation(RestController.class))
//            .paths(regex("/api/.*").or(regex("/public/.*")))
//            .build()
//                .securityContexts(List.of(securityContext()))
//                .securitySchemes(List.of(apiKey()))
//                .apiInfo(apiEndPointsInfo());
//    }
//
//    protected ApiInfo apiEndPointsInfo() {
//        return new ApiInfoBuilder().title("simple crud REST APIs")
//            .description("click on upper link for getting swagger file")
//            .contact(new Contact("miladro", "www.miladro.com", "support@miladro.com"))
//            .license("Apache 2.0")
//            .licenseUrl("http://www.miladro.com")
//            .version("1.0.0")
//            .build();
//
//    }
//
//    protected ApiKey apiKey() {
//        return new ApiKey("JWT_TOKEN", AUTHORIZATION_HEADER, "header");
//    }
//
//    protected SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(defaultAuth())
//                .forPaths(PathSelectors.ant(DEFAULT_INCLUDE_PATTERN))
//                .build();
//    }
//
//    protected List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope
//                = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return List.of(
//                new SecurityReference("JWT_TOKEN", authorizationScopes));
//    }
//}