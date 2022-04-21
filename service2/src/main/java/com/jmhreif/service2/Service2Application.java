package com.jmhreif.service2;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class Service2Application {

	public static void main(String[] args) {
		SpringApplication.run(Service2Application.class, args);
	}

	@Bean
	WebClient client() {
		return WebClient.create("http://localhost:8081");
	}

}

@RestController
@AllArgsConstructor
@RequestMapping("/goodreads")
class BookController {
	private final WebClient client;

	@GetMapping("/books")
	Flux<Book> getBooks() {
		return client.get()
				.uri("/db/books")
				.retrieve()
				.bodyToFlux(Book.class);
	}
}

@Data
class Book {
	private String bookID;
	private String title;
	private String authors;
}
