package com.alura.literalura.services;

import com.alura.literalura.dto.DadosLivro;
import com.alura.literalura.dto.DadosResposta;
import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Livro;
import com.alura.literalura.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;


@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final AutorService autorService;
    private final ConverteDados converteDados;
    private final ConsumoApi consumoApi;

    public LivroService(ConverteDados converteDados, ConsumoApi consumoApi, LivroRepository livroRepository, AutorService autorService) {
        this.livroRepository = livroRepository;
        this.converteDados = converteDados;
        this.consumoApi = consumoApi;
        this.autorService = autorService;
    }

    public List<DadosLivro> buscaLivrosNome(String nomeLivro) {
        String urlPesquisa = "?search=" + nomeLivro.toLowerCase().replace(" ", "-");

        var json = consumoApi.obterDados(urlPesquisa);

        return converteDados.obterDados(json, DadosResposta.class)
                .livro().stream()
                .distinct().sorted(Comparator.comparing(DadosLivro::titulo)).toList();
    }


    public List<DadosLivro> buscaTodosLivros() {
        String urlTodosLivros = "";

        var json = consumoApi.obterDados(urlTodosLivros);

        return converteDados.obterDados(json, DadosResposta.class)
                .livro().stream()
                .distinct().sorted(Comparator.comparing(DadosLivro::titulo)).toList();
    }

    public void salvaDadosTabelasLivroAutor(int paginaInicial, int paginaFinal) {

        for (int i = paginaInicial; i <= paginaFinal; i++) {
            var json = consumoApi.obterDados("?page=" + i);

            List<DadosLivro> dadosLivros = converteDados.obterDados(json, DadosResposta.class).livro();

            dadosLivros.forEach(dadosLivro -> {
                if (!livroRepository.existsByTitulo(dadosLivro.titulo())) {

                    List<Autor> autores = dadosLivro.autor().stream()
                                    .map(autorService::salvarAutor)
                                            .toList();

                    Livro livro = new Livro(
                            dadosLivro.titulo(),
                            autores,
                            Objects.requireNonNullElse(dadosLivro.idioma().getFirst(), "Idioma Não Informado"),
                            Objects.requireNonNullElse(dadosLivro.numeroDownloads(), 0)
                    );

                    livroRepository.save(livro);
                }
            });
        }
    }

    public double quantidadeLivrosIdiomaBancoDados(String idioma) {
        List<Livro> livros = livroRepository.findAll();

        return livros.stream().filter(l -> l.getIdioma().equals(idioma)).count();
    }

    public List<Livro> listaLivrosPorIdioma(String idioma) {
        List<Livro> livros = livroRepository.findAll();

        return livros.stream()
                .filter(l -> l.getIdioma().equals(idioma))
                .toList().stream()
                .sorted(Comparator.comparing(Livro::getTitulo))
                .toList();

    }
}

