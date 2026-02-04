package com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.jetbrains.annotations.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(@JsonAlias ({"name", "nome"}) String nome,
                         @JsonAlias ({"birth_year", "anoNascimento"}) Integer anoNascimento,
                         @JsonAlias ({"death_year", "anoFalecimento"}) Integer anoFalecimento) {

    @Override
    public @NotNull String toString() {
        return "Autor='" + nome + '\'' +
                ",\n Ano de Nascimento=" + anoNascimento +
                ",\n Ano de Falecimento=" + anoFalecimento;
    }
}
