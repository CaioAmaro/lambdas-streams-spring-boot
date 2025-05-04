package br.alura.com.lambdas_streams_spring_boot.desafio.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosVeiculo(@JsonAlias("Valor") String valor,
                           @JsonAlias("Marca") String modelo,
                           @JsonAlias("Modelo") String anoModelo,
                           @JsonAlias("AnoModelo") String combusitvel,
                           @JsonAlias("Combustivel") String codigoFipe,
                           @JsonAlias("CodigoFipe") String mesReferencia,
                           @JsonAlias("SiglaCombustivel") String siglaCombustivel) {

}
