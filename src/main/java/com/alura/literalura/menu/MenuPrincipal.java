package com.alura.literalura.menu;


import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuPrincipal {

    static Scanner scanner = new Scanner(System.in);

    private final MenuAutores menuAutores;
    private final MenuLivros menuLivros;

    public MenuPrincipal(MenuLivros menuLivros, MenuAutores menuAutores) {
        this.menuAutores = menuAutores;
        this.menuLivros = menuLivros;
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
                5. Popular Tabelas
                9. Sair
                """);


        int opcao = scanner.nextInt();
        scanner.nextLine();


        while (opcao != 9) {
            switch (opcao) {
                case 1 -> menuLivros.imprimeLivroPorNome();
                case 2 -> menuLivros.imprimeTodosLivros();
                case 3 -> menuAutores.imprimeAutores();
                case 4 -> menuAutores.imprimeAutoresVivosEmAno();
                case 5 -> menuLivros.popularTabelas();
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println("""
                    \nMenu de Opções:
                    1. Buscar Livro por Nome
                    2. Listar Todos os Livros
                    3. Lista de Autores
                    4. Listar Autores Vivos em Determinado Ano
                    5. Popular Tabelas
                    9. Sair
                    """);
            opcao = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.println("Obrigado por usar o Literalura, " + nome + "! Até a próxima leitura!");
        scanner.close();
    }

}
