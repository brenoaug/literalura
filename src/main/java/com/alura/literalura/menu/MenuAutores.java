package com.alura.literalura.menu;

import com.alura.literalura.dto.DadosAutor;
import com.alura.literalura.services.AutorService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static com.alura.literalura.menu.MenuLivros.scanner;

@Component
public class MenuAutores {

    private final AutorService autorService;

    public MenuAutores(AutorService autorService) {
        this.autorService = autorService;
    }

    public void imprimeAutores() {
        System.out.println("Listando todos os autores disponíveis...\n");

        var listaParaImpressao = autorService.buscaAutores()
                .stream()
                .map(DadosAutor::toString)
                .collect(Collectors.joining(",\n\n "));

        System.out.println(listaParaImpressao);
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
//
//    public void popularTabelaAutores() {
//        System.out.println("Você que começar de qual pagina\n");
//        int paginaInicial = scanner.nextInt();
//        scanner.nextLine();
//
//        System.out.println("Você que terminar em qual pagina\n");
//        int paginaFinal = scanner.nextInt();
//        scanner.nextLine();
//
//        System.out.println("Populando tabela de autores...\n");
//
//        for(int i = paginaInicial; i <= paginaFinal; i++) {
//            var json = consumoApi.obterDados("?page=" + i);
//
//            List<DadosAutor> dadosAutores = converteDados.obterDados(json, DadosResposta.class).livro().stream()
//                    .flatMap(l -> l.autor().stream())
//                    .distinct().sorted(Comparator.comparing(DadosAutor::nome)).toList();
//
//            dadosAutores.forEach(dadosAutor -> {
//                if(!autorRepository.existsByNome(dadosAutor.nome())) {
//                    autorRepository.save(new Autor(
//                            dadosAutor.nome(),
//                            Objects.requireNonNullElse(dadosAutor.anoNascimento(), 0),
//                            Objects.requireNonNullElse(dadosAutor.anoFalecimento(), 0))
//                    );
//                }});
//
//            System.out.println(dadosAutores.stream().map(DadosAutor::toString).collect(joining(",\n\n ")));
//        }
}


