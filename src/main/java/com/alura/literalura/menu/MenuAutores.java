package com.alura.literalura.menu;

import com.alura.literalura.dto.DadosAutor;
import com.alura.literalura.services.AutorService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static com.alura.literalura.menu.MenuPrincipal.scanner;

@Component
public class MenuAutores {

    private final AutorService autorService;

    public MenuAutores(AutorService autorService) {
        this.autorService = autorService;
    }

    public void imprimeAutores() {
        System.out.println("Listando todos os autores disponíveis...\n");

        autorService.buscaAutores()
                .forEach(autor -> System.out.println(autor + "\n"));
    }

    public void imprimeAutoresVivosEmAno() {
        System.out.println("Digite o ano inicial:");
        var anoInicial = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite o ano final:");
        var anoFinal = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Listando autores vivos entre os anos " + anoInicial + " e " + anoFinal + "...\n");

        var listaParaImpressao = autorService.buscaAutoresPorAnoNascimentoFalecimento(anoInicial, anoFinal)
                .stream()
                .map(DadosAutor::toString)
                .collect(Collectors.joining(",\n "));


        System.out.println(listaParaImpressao);
    }

    public void imprimeAutoresVivosEmAnoNoBanco() {
        var anoInicial = 0;
        var anoFinal = 0;

        while (anoFinal <= anoInicial) {
            System.out.println("Digite o ano inicial:");
            anoInicial = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Digite o ano final:");
            anoFinal = scanner.nextInt();
            scanner.nextLine();

            System.out.println("O ano final deve ser maior que o ano inicial. Tente novamente.\n");
        }

        System.out.println("Listando autores vivos entre os anos " + anoInicial + " e " + anoFinal + "...\n");

        var listaParaImpressao = autorService.buscaAutoresPorAnoNascimentoFalecimentoNoBanco(anoInicial, anoFinal)
                .stream()
                .map(a -> a.getNome() + " (Nascimento: " + a.getAnoNascimento() + ", Falecimento: " + a.getAnoFalecimento() + ")")
                .collect(Collectors.joining(",\n"));



        System.out.println(!listaParaImpressao.isBlank() ?
                listaParaImpressao : "Nenhum autor encontrado para o período informado.\n");
    }
}


