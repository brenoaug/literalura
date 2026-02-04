package com.alura.literalura;

import com.alura.literalura.dto.DadosAutor;
import com.alura.literalura.dto.DadosLivro;
import com.alura.literalura.dto.DadosResposta;
import com.alura.literalura.services.ConsumoApi;
import com.alura.literalura.services.ConverteDados;

import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    Scanner scanner = new Scanner(System.in);
    ConsumoApi consumoApi = new ConsumoApi();
    ConverteDados converteDados = new ConverteDados();

    public Principal() {
    }

    public void iniciar() {

        System.out.println("""
                =================================================
                Bem-vindo ao Literalura - Sua Biblioteca Virtual!
                Pesquise sobre seus livros.
                =================================================
                """);
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        System.out.println("\nOlá, " + nome + "! Vamos explorar o mundo dos livros juntos.");
        System.out.println("""
                \nMenu de Opções:
                1. Buscar Livro por Nome
                2. Listar Todos os Livros
                3. Lista de Autores
                4. Listar Autores Vivos em Determinado Ano
                9. Sair
                """);


        int opcao = scanner.nextInt();
        scanner.nextLine();


        while (opcao != 9) {
            switch (opcao) {
                case 1 -> buscarLivroPorNome();
                case 2 -> listarTodosLivros();
                case 3 -> listarAutores();
                case 4 -> listarAutoresVivosEmAno();
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println("""
                    \nMenu de Opções:
                    1. Buscar Livro por Nome
                    2. Listar Todos os Livros
                    3. Lista de Autores
                    4. Listar Autores Vivos em Determinado Ano
                    9. Sair
                    """);
            opcao = scanner.nextInt();
            scanner.nextLine();
        }
    }

    public void buscarLivroPorNome() {
        System.out.print("Digite o nome do livro que deseja buscar: ");

        String nomeLivro = scanner.nextLine();

        String urlPesquisa = "?search=" + nomeLivro.toLowerCase().replace(" ", "-");

        System.out.println("Buscando informações sobre o livro: " + nomeLivro.toUpperCase() + "\n");

        var json = consumoApi.obterDados(urlPesquisa);

        String resultadoPesquisa = converteDados.obterDados(json, DadosResposta.class).livro().stream()
                .map(DadosLivro::toString).collect(Collectors.joining("\n\n"));

        System.out.println(resultadoPesquisa);
    }

    public void listarTodosLivros() {
        System.out.println("Listando todos os livros disponíveis...\n");

        String urlTodosLivros = "";

        var json = consumoApi.obterDados(urlTodosLivros);

        String resultadoLista = converteDados.obterDados(json, DadosResposta.class).livro().stream()
                .map(DadosLivro::toString).collect(Collectors.joining("\n\n"));

        System.out.println(resultadoLista);
    }

    public void listarAutores() {
        System.out.println("Listando todos os autores disponíveis...\n");

        String urlTodosAutores = "";

        var json = consumoApi.obterDados(urlTodosAutores);

        String resultadoLista = converteDados.obterDados(json, DadosResposta.class).livro().stream()
                .flatMap(l -> l.autor().stream())
                .map(DadosAutor::toString).collect(Collectors.joining(",\n\n "));

        System.out.println(resultadoLista);
    }

    public void listarAutoresVivosEmAno() {
        System.out.println("Digite o ano inicial:");
        int anoInicial = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite o ano final:");
        int anoFinal = scanner.nextInt();
        scanner.nextLine();

        String urlAutoresVivos = "?author_year_start=" + anoInicial + "&author_year_end=" + anoFinal;

        var json = consumoApi.obterDados(urlAutoresVivos);

        String resultadoLista = converteDados.obterDados(json, DadosResposta.class).livro().stream()
                .flatMap(l -> l.autor().stream()).distinct()
                .sorted(Comparator.comparing(DadosAutor::nome))
                .map(DadosAutor::toString).collect(Collectors.joining(",\n\n "));

        System.out.println(resultadoLista);
    }


}
