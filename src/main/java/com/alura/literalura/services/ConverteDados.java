package com.alura.literalura.services;


import com.alura.literalura.services.interfaces.IConverteDados;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ConverteDados implements IConverteDados {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {

        if(json == null || json.isEmpty()){
            throw new IllegalArgumentException("O JSON fornecido é nulo ou vazio.");
        }

        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

}
