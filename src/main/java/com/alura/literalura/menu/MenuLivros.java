package com.alura.literalura.menu;

import com.alura.literalura.dto.DadosLivro;
import com.alura.literalura.dto.DadosResposta;
import com.alura.literalura.services.ConsumoApi;
import com.alura.literalura.services.ConverteDados;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static java.util.stream.Collectors.joining;

@Component
public class MenuLivros {

    static ConsumoApi consumoApi = new ConsumoApi();
    static ConverteDados converteDados = new ConverteDados();
    static Scanner scanner = new Scanner(System.in);


    public static void buscarLivroPorNome() {

        System.out.print("Digite o nome do livro que deseja buscar: ");

        String nomeLivro = scanner.nextLine();

        String urlPesquisa = "?search=" + nomeLivro.toLowerCase().replace(" ", "-");

        System.out.println("Buscando informações sobre o livro: " + nomeLivro.toUpperCase() + "\n");

        var json = consumoApi.obterDados(urlPesquisa);

        String resultadoPesquisa = converteDados.obterDados(json, DadosResposta.class).livro().stream()
                .map(DadosLivro::toString).collect(joining("\n\n"));

        System.out.println(resultadoPesquisa);
    }

    public static void listarTodosLivros() {
        System.out.println("Listando todos os livros disponíveis...\n");

        String urlTodosLivros = "";

        var json = consumoApi.obterDados(urlTodosLivros);

        String resultadoLista = converteDados.obterDados(json, DadosResposta.class).livro().stream()
                .map(DadosLivro::toString).collect(joining("\n\n"));

        System.out.println(resultadoLista);
    }

}
