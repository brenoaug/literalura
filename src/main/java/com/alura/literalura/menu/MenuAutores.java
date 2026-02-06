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
                .collect(Collectors.joining(",\n\n "));


        System.out.println(listaParaImpressao);
    }
}


