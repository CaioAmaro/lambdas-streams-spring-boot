package br.alura.com.lambdas_streams_spring_boot.desafio.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosModelo(@JsonAlias("modelos") List<Dados> lista) {
}
