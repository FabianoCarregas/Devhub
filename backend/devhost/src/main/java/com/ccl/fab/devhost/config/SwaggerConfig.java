package com.ccl.fab.devhost.config;

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

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ccl.fab.devhost.resources"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
	    return new ApiInfoBuilder()
	    		.title("Devhost v1")
	    		.description("This api will be used to create an open source platform to storage a person contacts, work details and projects in an easely way ")
	    		.termsOfServiceUrl("http://localhost")
	    		.licenseUrl("http://localhosts")
	    		.version("v1")
	    		.contact(contact())
	    		.build();
	}
	
	private Contact contact() {
		return new Contact("Fabiano Carregas",
				"https://github.com/FabianoCarregas",
				"fabianofac_@hotmail.com");
		
	}
	
	
}
