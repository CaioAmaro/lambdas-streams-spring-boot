package br.alura.com.lambdas_streams_spring_boot.desafio.models;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Veiculo {
    private String valor;
    private String modelo;
    private String anoModelo;
    private String combusitvel;
    private String codigoFipe;
    private String mesReferencia;
    private String siglaCombustivel;

    public Veiculo(DadosVeiculo dadosVeiculo){
        this.valor = dadosVeiculo.valor();
        this.modelo = dadosVeiculo.modelo();
        this.anoModelo = dadosVeiculo.anoModelo();
        this.combusitvel = dadosVeiculo.combusitvel();
        this.codigoFipe = dadosVeiculo.codigoFipe();
        this.mesReferencia = dadosVeiculo.mesReferencia();
        this.siglaCombustivel = dadosVeiculo.siglaCombustivel();
    }

    @Override
    public String toString() {
        return "Veiculo {" +
                "\n  valor='" + valor + '\'' +
                ",\n  modelo='" + modelo + '\'' +
                ",\n  anoModelo='" + anoModelo + '\'' +
                ",\n  combustivel='" + combusitvel + '\'' +
                ",\n  codigoFipe='" + codigoFipe + '\'' +
                ",\n  mesReferencia='" + mesReferencia + '\'' +
                ",\n  siglaCombustivel='" + siglaCombustivel + '\'' +
                "\n}";
    }

}
