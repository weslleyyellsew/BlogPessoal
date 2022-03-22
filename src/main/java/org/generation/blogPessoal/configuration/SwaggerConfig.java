package org.generation.blogPessoal.configuration;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration

public class SwaggerConfig {
	
	@Bean
	public OpenAPI springBlogPessoalOpenAPI() {
		
		return new OpenAPI()
				.info(new Info()
					.title("Projeto Blog Pessoal - Stonewall ")
					.description("Projeto Blog Pessoal - Generation Brasil - Com a visão de que a falta de conhecimento gera a base da ignorancia e do preconceito... Esse blog foi criado para mim, para você e pra quem quiser saber mais e conhecer a história, as lutas, as conquistas da comunidade LGBTQIA+.")
					.version("v1.0.0")
				.license(new License()
					.name("generation.org.br")
					.url("http://springdoc.org"))
				.contact(new Contact()
					.name("Weslley Santos")
					.url("https://github.com/larissadecampos")
					.email("weslley16santos42@gmail.com")))
				.externalDocs(new ExternalDocumentation()
					.description("Github")
					.url("https://github.com/weslleyyellsew"));
	}

	@Bean
	public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {

		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {

				ApiResponses apiResponses = operation.getResponses();

				apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
				apiResponses.addApiResponse("201", createApiResponse("Objeto persistido!"));
				apiResponses.addApiResponse("204", createApiResponse("Objeto excluído!"));
				apiResponses.addApiResponse("400", createApiResponse("Erro na requisição!"));
				apiResponses.addApiResponse("401", createApiResponse("Acesso não autorizado!"));
				apiResponses.addApiResponse("404", createApiResponse("Objeto não encontrado!"));
				apiResponses.addApiResponse("500", createApiResponse("Erro na aplicação!"));

			}));
		};
	}

	private ApiResponse createApiResponse(String message) {

		return new ApiResponse().description(message);

	}


}