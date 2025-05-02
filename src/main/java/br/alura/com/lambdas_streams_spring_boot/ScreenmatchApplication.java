package br.alura.com.lambdas_streams_spring_boot;

import br.alura.com.lambdas_streams_spring_boot.service.ConsumoApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var json = ConsumoApi.obterDados("https://coffee.alexflipnote.dev/random.json");
		System.out.println(json);
	}
}
