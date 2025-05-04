package br.alura.com.lambdas_streams_spring_boot;

import br.alura.com.lambdas_streams_spring_boot.desafio.main.MainDesafio;
import br.alura.com.lambdas_streams_spring_boot.main.Main;
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

//Curso		Main principal = new Main();
//		principal.exibeMenu();

		//DESAFIO TABELA FIPE.
		MainDesafio mainDesafio = new MainDesafio();
		mainDesafio.executar();

	}
}
