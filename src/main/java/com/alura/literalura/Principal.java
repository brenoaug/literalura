package com.alura.literalura;

import com.alura.literalura.dto.DadosAutor;
import com.alura.literalura.dto.DadosLivro;
import com.alura.literalura.dto.DadosResposta;
import com.alura.literalura.model.Autor;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LivroRepository;
import com.alura.literalura.services.ConsumoApi;
import com.alura.literalura.services.ConverteDados;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


import static java.util.stream.Collectors.joining;


public class Principal {

    Scanner scanner = new Scanner(System.in);
    ConsumoApi consumoApi = new ConsumoApi();
    ConverteDados converteDados = new ConverteDados();

    LivroRepository livroRepository;
    AutorRepository autorRepository;

    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
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
                5. Popular Tabela de Autores
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
                case 5 -> popularTabelaAutores();
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
                .map(DadosLivro::toString).collect(joining("\n\n"));

        System.out.println(resultadoPesquisa);




    }

    public void listarTodosLivros() {
        System.out.println("Listando todos os livros disponíveis...\n");

        String urlTodosLivros = "";

        var json = consumoApi.obterDados(urlTodosLivros);

        String resultadoLista = converteDados.obterDados(json, DadosResposta.class).livro().stream()
                .map(DadosLivro::toString).collect(joining("\n\n"));

        System.out.println(resultadoLista);
    }

    public void listarAutores() {
        System.out.println("Listando todos os autores disponíveis...\n");

//        String resultadoLista = converteDados.obterDados(json, DadosResposta.class).livro().stream()
//                .flatMap(l -> l.autor().stream())
//                .distinct().sorted(Comparator.comparing(DadosAutor::nome))
//                .map(DadosAutor::toString).collect(joining(",\n\n "));
        var json = consumoApi.obterDados("");

        List<DadosAutor> dadosAutores = converteDados.obterDados(json, DadosResposta.class).livro().stream()
                .flatMap(l -> l.autor().stream())
                .distinct().sorted(Comparator.comparing(DadosAutor::nome)).toList();

        System.out.println(dadosAutores.stream().map(DadosAutor::toString).collect(joining(",\n\n ")));

    }

    public void listarAutoresVivosEmAno() {
        System.out.println("Digite o ano inicial:");
        int anoInicial = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite o ano final:");
        int anoFinal = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nListando autores vivos entre os anos " + anoInicial + " e " + anoFinal + "...\n");

        var json = consumoApi.obterDados("?author_year_start=" + anoInicial + "&author_year_end=" + anoFinal);

        String resultadoLista = converteDados.obterDados(json, DadosResposta.class).livro().stream()
                .flatMap(l -> l.autor().stream()).distinct()
                .sorted(Comparator.comparing(DadosAutor::nome))
                .map(DadosAutor::toString).collect(joining(",\n\n "));

        System.out.println(resultadoLista);
    }

    public void popularTabelaAutores() {
        System.out.println("Você que começar de qual pagina\n");
        int paginaInicial = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Você que terminar em qual pagina\n");
        int paginaFinal = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Populando tabela de autores...\n");

        for(int i = paginaInicial; i <= paginaFinal; i++) {
            var json = consumoApi.obterDados("?page=" + i);

            List<DadosAutor> dadosAutores = converteDados.obterDados(json, DadosResposta.class).livro().stream()
                    .flatMap(l -> l.autor().stream())
                    .distinct().sorted(Comparator.comparing(DadosAutor::nome)).toList();

            dadosAutores.forEach(dadosAutor -> {
                if(!autorRepository.existsByNome(dadosAutor.nome())) {
                    autorRepository.save(new Autor(
                                            dadosAutor.nome(),
                                            Objects.requireNonNullElse(dadosAutor.anoNascimento(), 0),
                                            Objects.requireNonNullElse(dadosAutor.anoFalecimento(), 0))
                            );
                }});

            System.out.println(dadosAutores.stream().map(DadosAutor::toString).collect(joining(",\n\n ")));
        }
    }
}
