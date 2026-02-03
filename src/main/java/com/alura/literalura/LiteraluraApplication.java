package com.alura.literalura;

import com.alura.literalura.dto.DadosLivro;
import com.alura.literalura.services.ConsumoApi;
import com.alura.literalura.services.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) {
		var consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados();

		ConverteDados conversor = new ConverteDados();
		DadosLivro dados = conversor.obterDados(json, DadosLivro.class);
		System.out.println(dados);
	}
}
