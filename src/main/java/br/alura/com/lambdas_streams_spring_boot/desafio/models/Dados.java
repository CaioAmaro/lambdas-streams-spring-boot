package br.alura.com.lambdas_streams_spring_boot.desafio.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Dados(String codigo,
                    String nome) {
}
