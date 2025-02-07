package com.asamoah.accounts;

import com.asamoah.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice REST API Documentation",
				version = "v1",
				description = "EazyBank Accounts microservice REST API Documentation",
				contact = @Contact(
						name = "Emmanuel Akwasi Asamoah",
						email = "akwasiemmanuel64@gmail.com",
						url = "https://www.linkedin.com/in/emmanuel-akwasi-asamoah-7b1b3b1b3/"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.linkedin.com/in/emmanuel-akwasi-asamoah-7b1b3b1b3/"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "EazyBank Accounts microservice REST API Documentation",
				url = "https://www.linkedin.com/in/emmanuel-akwasi-asamoah-7b1b3b1b3/"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
		System.out.println("Hello World");

	}

}
