/*
package com.example.springsecuritymysql.config;
*/
/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/*
    swagger generates the REST API documents for RESTful web services.
    It provides a user interface to access our RESTful web services via the web browser.
    https://www.tutorialspoint.com/spring_boot/spring_boot_enabling_swagger2.htm

    @EnableSwagger2 enables the swagger for the application, The docket Bean is told the base api
    to create a swagger for, the Rest End points are written by spring-boot-starter-web, extra dependencies
    have been added to the pom.xml file for configuration.

    https://swagger.io/docs/specification/api-general-info/

    see swagger ui at this url  http://localhost:8080/swagger-ui.html#/

 */
/*
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("Transfers Swagger Page")
                .description("REST api testing point for all controllers")
                .contact(new Contact("PK Swagger", "", "#"))
                .version("1.0.0")
                .build();
    }

}
*/