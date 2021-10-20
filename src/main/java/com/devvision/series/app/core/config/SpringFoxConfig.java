package com.devvision.series.app.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {
	@Bean
	public Docket swagger() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
					.apis(RequestHandlerSelectors.any())
					.paths(PathSelectors.any())
					.build()
				.apiInfo(metaData());
	}
	
	private ApiInfo metaData() {
		return new ApiInfoBuilder()
				.title("Documentação API Leauto Seguros")
				.description("Api de gestão de seguros")
				.version("1.0")
				.contact(new Contact("Luiz Rodrigues", "http://localhost:8080/swagger-ui/index.html", "atende@webvirtua.com.br"))
				.license("Proprietário")
				.licenseUrl("http://localhost:8080")
				.build();
	}
}
