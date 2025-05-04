package br.com.konvo.konvo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class KonvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KonvoApplication.class, args);
	}

}
