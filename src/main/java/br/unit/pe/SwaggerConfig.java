package br.unit.pe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.VendorExtension;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		Set<String> responseProduceType = new HashSet<String>();
		responseProduceType.add("application/json");
		responseProduceType.add("application/xml");
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
				.build().useDefaultResponseMessages(false).genericModelSubstitutes(ResponseEntity.class)
				.produces(responseProduceType).consumes(responseProduceType).apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {
		String title = "API IODev";
		String description = "Todas as informações relacionadas";
		String version = "1.0";
		String termsOfServiceUrl = "";
		
		String contactName = "André Fillipe";
		String contactUrl = "https://github.com/andrefillipedev/";
		String contactEmail = "andre.fillipe20@gmail.com";
		springfox.documentation.service.Contact contact = new springfox.documentation.service.Contact(contactName,contactUrl,contactEmail);
		
		String license = "";//licenca da API
		String licenseUrl = "";
		
		List<VendorExtension> extensions = new ArrayList<>();
		
		ApiInfo apiInfo = new ApiInfo(title, description, version, termsOfServiceUrl,
				contact,license , licenseUrl,extensions );
		return apiInfo;

	}
	/*private Object apiKey() {
		return null;
	}*/
}
