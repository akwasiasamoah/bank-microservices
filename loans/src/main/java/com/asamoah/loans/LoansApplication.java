package com.asamoah.loans;

import com.asamoah.loans.dto.LoansContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.eazybytes.loans.controller") })
@EnableJpaRepositories("com.eazybytes.loans.repository")
@EntityScan("com.eazybytes.loans.model")*/
@EnableConfigurationProperties(value = {LoansContactInfoDto.class})
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")

@OpenAPIDefinition(
		info = @Info(
				title = "Loans microservice REST API Documentation",
				description = "EazyBank Loans microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Emmanuel Asamoah",
						email = "akwasiemmanuel64@gmail.com",
						url = "https://www.linkedin.com/in/emmanuel-akwasi-asamoah-7b1b3b1b3/"

				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.linkedin.com/in/emmanuel-akwasi-asamoah-7b1b3b1b3/"

				)
		),
		externalDocs = @ExternalDocumentation(
				description = "EazyBank Loans microservice REST API Documentation",
				url = "https://www.linkedin.com/in/emmanuel-akwasi-asamoah-7b1b3b1b3/"

		)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
