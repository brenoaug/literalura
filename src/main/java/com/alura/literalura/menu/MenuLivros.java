package com.alura.literalura.menu;

import com.alura.literalura.services.LivroService;
import org.springframework.stereotype.Component;

import static com.alura.literalura.menu.MenuPrincipal.scanner;

@Component
public class MenuLivros {

    private final LivroService livroService;

    public MenuLivros(LivroService livroService) {
        this.livroService = livroService;
    }

    public void imprimeLivroPorNome() {
        System.out.print("Digite o nome do livro que deseja buscar: ");
        String nomeLivro = scanner.nextLine();

        System.out.println("Buscando informações sobre o livro: " + nomeLivro.toUpperCase() + "\n");

        livroService.buscaLivrosNome(nomeLivro)
                .forEach(livro -> System.out.println(livro + "\n"));
    }

    public void imprimeTodosLivros() {
        System.out.println("Listando todos os livros disponíveis...\n");

        livroService.buscaTodosLivros()
                .forEach(livro -> System.out.println(livro + "\n"));
    }

    public void popularTabelas() {
        System.out.println("Você que começar de qual pagina\n");
        int paginaInicial = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Você que terminar em qual pagina\n");
        int paginaFinal = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Populando tabelas...\n");

        livroService.salvaDadosTabelasLivroAutor(paginaInicial, paginaFinal);

        System.out.println("Tabela populada com sucesso!\n");
    }

    public void imprimeLivrosPorIdioma() {
        System.out.println("""
                Escolha entre os seguintes idiomas:
                
                1. Inglês
                2. Frances
                3. Espanhol
                4. Português
                0. Voltar ao menu principal
                
                """);

        var opcao = scanner.nextInt();
        scanner.nextLine();

        String idiomaCompleto = "";
        String idioma = "";

        while (opcao != 0) {

            switch (opcao) {
                case 1:
                    idioma = "en";
                    idiomaCompleto = "Inglês";
                    break;
                case 2:
                    idioma = "fr";
                    idiomaCompleto = "Francês";
                    break;
                case 3:
                    idioma = "es";
                    idiomaCompleto = "Espanhol";
                    break;
                case 4:
                    idioma = "pt";
                    idiomaCompleto = "Português";
                    break;
                default:
                    System.out.println("Idioma inválido. Tente novamente.");
            }

            var quantidade = livroService.quantidadeLivrosIdiomaBancoDados(idioma);
            var listaLivros = livroService.listaLivrosPorIdioma(idioma);

            System.out.printf("""
                    A quantidade de livros no idioma %s é: %.0f
                    %n""", idiomaCompleto, quantidade);

            System.out.println("Listando livros do idioma: \n\n" + listaLivros.toString().replace("[", "").replace("]", "").replace(", ", ""));

            System.out.println("""
                    Escolha entre os seguintes idioma:
                    
                    1. Inglês
                    2. Frances
                    3. Espanhol
                    4. Português
                    0. Voltar ao menu principal
                    
                    """);

            opcao = scanner.nextInt();
            scanner.nextLine();
        }

    }

}

