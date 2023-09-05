package org.rhmodding.saffronrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages= "org.rhmodding.saffronrest")
@ComponentScan
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories("org.rhmodding.saffronrest.repositories")
public class SaffronRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaffronRestApplication.class, args);
	}

}
