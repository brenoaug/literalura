package com.alura.literalura.services.interfaces;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> tipoClasse);
}
