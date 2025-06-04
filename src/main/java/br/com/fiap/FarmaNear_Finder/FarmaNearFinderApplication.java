package br.com.fiap.FarmaNear_Finder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FarmaNearFinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmaNearFinderApplication.class, args);
	}

}
