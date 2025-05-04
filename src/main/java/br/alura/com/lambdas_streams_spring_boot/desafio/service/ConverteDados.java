package br.alura.com.lambdas_streams_spring_boot.desafio.service;

import br.alura.com.lambdas_streams_spring_boot.desafio.models.Dados;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConverteDados {
    ObjectMapper mapper = new ObjectMapper();

//    public Dados obter(String json, Class<Dados> classe){
//
//        try {
//            return mapper.readValue(json, classe);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public <T> T obter(String json, Class<T> classe){

        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> obterLista(String json, Class<T> classe){

        CollectionType lista = mapper.getTypeFactory()
                .constructCollectionType(List.class, classe);

        try {
            return mapper.readValue(json, lista);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
