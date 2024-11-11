package com.example.talk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;

@Configuration
public class TestTalkApplication {

	@Bean
	@ServiceConnection
	@RestartScope
	PostgreSQLContainer<?> postgresContainer() {
		PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");
		postgres.withExposedPorts(32785);
		return postgres;
	}

	public static void main(String[] args) {
		SpringApplication
				.from(TalkApplication::main)
				.with(TestTalkApplication.class)
				.run(args);
	}

}
