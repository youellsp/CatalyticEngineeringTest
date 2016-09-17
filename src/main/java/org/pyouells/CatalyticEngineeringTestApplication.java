package org.pyouells;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"org.pyouells"})
public class CatalyticEngineeringTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalyticEngineeringTestApplication.class, args);
	}

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("org.pyouells.controllers")
				.apiInfo(apiInfo())
				.select()
				.paths(regex("/service/.*"))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Micro-service Spring Boot Engineering Test")
				.description("for: OIAM - Catalytic")
				.contact("Patrick Youells")
				.version("1.0")
				.build();
	}
}


