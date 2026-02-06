package com.alura.literalura.services;

import com.alura.literalura.dto.DadosAutor;
import com.alura.literalura.dto.DadosResposta;
import com.alura.literalura.model.Autor;
import com.alura.literalura.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class AutorService {
    private final ConverteDados converteDados;
    private final ConsumoApi consumoApi;
    private final AutorRepository autorRepository;

    public AutorService(ConverteDados converteDados, ConsumoApi consumoApi, AutorRepository autorRepository) {
        this.converteDados = converteDados;
        this.consumoApi = consumoApi;
        this.autorRepository = autorRepository;
    }

    public List<DadosAutor> buscaAutores() {
        var json = consumoApi.obterDados("");

        return converteDados.obterDados(json, DadosResposta.class).livro().stream()
                .flatMap(l -> l.autor().stream())
                .distinct().sorted(Comparator.comparing(DadosAutor::nome)).toList();
    }

    public List<DadosAutor> buscaAutoresPorAnoNascimentoFalecimento(int anoInicial, int anoFinal) {
        var json = consumoApi.obterDados("?author_year_start=" + anoInicial + "&author_year_end=" + anoFinal);

        return converteDados.obterDados(json, DadosResposta.class).livro().stream()
                .flatMap(l -> l.autor().stream()).distinct()
                .sorted(Comparator.comparing(DadosAutor::nome)).toList();
    }

    public List<Autor> buscaAutoresPorAnoNascimentoFalecimentoNoBanco(int anoInicial, int anoFinal) {
        return autorRepository.findAutorByVivo(anoInicial, anoFinal);
    }

    public Autor salvarAutor(DadosAutor dadosAutor) {
        String nomeAutor = Objects.requireNonNullElse(dadosAutor.nome(), "Autor Desconhecido");

        return autorRepository.findByNome(nomeAutor).orElseGet(() -> {
            Autor autor = new Autor(
                    nomeAutor,
                    dadosAutor.anoNascimento(),
                    dadosAutor.anoFalecimento()
            );
            autorRepository.save(autor);
            return autor;
        });
    }
}
