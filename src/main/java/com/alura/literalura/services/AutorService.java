package com.alura.literalura.services;

import com.alura.literalura.dto.DadosAutor;
import com.alura.literalura.dto.DadosResposta;
import com.alura.literalura.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.joining;

@Service
public class AutorService {
    private final AutorRepository autorRepository;
    private final ConverteDados converteDados;
    private final ConsumoApi consumoApi;

    public AutorService(AutorRepository autorRepository, ConverteDados converteDados, ConsumoApi consumoApi) {
        this.autorRepository = autorRepository;
        this.converteDados = converteDados;
        this.consumoApi = consumoApi;
    }

    public List<DadosAutor> buscaAutores() {
        var json = consumoApi.obterDados("");
        var resultadoLista = converteDados.obterDados(json, DadosResposta.class).livro().stream()
                .flatMap(l -> l.autor().stream())
                .distinct().sorted(Comparator.comparing(DadosAutor::nome)).toList();

        return resultadoLista;
    }

    public List<DadosAutor> buscaAutoresPorAnoNascimentoFalecimento(int anoInicial, int anoFinal) {
        var json = consumoApi.obterDados("?author_year_start=" + anoInicial + "&author_year_end=" + anoFinal);

        var resultadoLista = converteDados.obterDados(json, DadosResposta.class).livro().stream()
                .flatMap(l -> l.autor().stream()).distinct()
                .sorted(Comparator.comparing(DadosAutor::nome)).toList();

        return resultadoLista;
    }
}
