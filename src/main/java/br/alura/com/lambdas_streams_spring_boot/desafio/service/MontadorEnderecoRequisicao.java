package br.alura.com.lambdas_streams_spring_boot.desafio.service;

public class MontadorEnderecoRequisicao {
    public String tipoVeiculo(int tipoVeiculo){
        String veiculo = "";

        if(tipoVeiculo == 1){
            veiculo = "motos";
        }

        if(tipoVeiculo == 2){
            veiculo = "carros";
        }

        if(tipoVeiculo == 3){
            veiculo = "caminhoes";
        }

        return "https://parallelum.com.br/fipe/api/v1/"+veiculo+"/marcas";

    }

    public String marcaVeiculo(int marcaVeiculo){
        return "/"+marcaVeiculo+"/modelos/";
    }

    public String modeloVeiculo(int modeloVeiculo){
        return modeloVeiculo+"/anos/";
    }

    public String anoModelo(String json, String ano){
        return json+ano;
    }

}
