package io.pivotal.hello;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.stream.Stream;

@Log
@SpringBootApplication
@EnableDiscoveryClient
public class HelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}

	@Profile("!cloud")
	@Bean
	CommandLineRunner loadData(GreetingRepository repository) {
		return commandLineRunner -> {
			Stream.of("English:Hello!", "French:Bonjour!", "Spanish:Hola!").forEach(token -> {
				String[] split = token.split(":");
				repository.save(new Greeting(null, split[0], split[1]));
			});
			repository.findAll().forEach(greeting -> log.info(greeting.toString()));
		};
	}
}
