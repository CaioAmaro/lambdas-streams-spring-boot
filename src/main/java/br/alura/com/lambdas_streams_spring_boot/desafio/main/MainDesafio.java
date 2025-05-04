package br.alura.com.lambdas_streams_spring_boot.desafio.main;

import br.alura.com.lambdas_streams_spring_boot.desafio.models.Dados;
import br.alura.com.lambdas_streams_spring_boot.desafio.models.DadosModelo;
import br.alura.com.lambdas_streams_spring_boot.desafio.models.DadosVeiculo;
import br.alura.com.lambdas_streams_spring_boot.desafio.models.Veiculo;
import br.alura.com.lambdas_streams_spring_boot.desafio.service.ConsumoApi;
import br.alura.com.lambdas_streams_spring_boot.desafio.service.ConverteDados;
import br.alura.com.lambdas_streams_spring_boot.desafio.service.MontadorEnderecoRequisicao;
import br.alura.com.lambdas_streams_spring_boot.desafio.service.Validador;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MainDesafio {
    private ConsumoApi consumoApi = new ConsumoApi();
    private Validador validador = new Validador();
    private MontadorEnderecoRequisicao montadorEnderecoRequisicao = new MontadorEnderecoRequisicao();
    private ConverteDados converteDados = new ConverteDados();

    public void executar(){
        System.out.println("Tipos de Veiculos");
        System.out.println("(1) - Motos");
        System.out.println("(2) - Carros");
        System.out.println("(3) - Caminhões");

        String endereco = montadorEnderecoRequisicao
                .tipoVeiculo(validador.validarTipoVeiculo());

        String json = consumoApi.obterDados(endereco);

        List<Dados> listaMarcas = converteDados.obterLista(json, Dados.class);

        System.out.println("\nMarcas\n");

        listaMarcas.forEach(m -> {
            System.out.println("("+m.codigo() + ") " + m.nome());
        });


        endereco += montadorEnderecoRequisicao.marcaVeiculo(validador.validarMarcaVeiculo(listaMarcas));
        json = consumoApi.obterDados(endereco);
        DadosModelo modelo = converteDados.obter(json, DadosModelo.class);

        System.out.println("\nModelos\n");
        modelo.lista().forEach(m -> {
            System.out.println("("+m.codigo() + ") " + m.nome());
        });

        var resultadoPesquisa = validador.validarPesquisaModelo(modelo.lista());

        System.out.println("\nResultado Pesquisa\n");
        resultadoPesquisa.forEach(m -> {
            System.out.println("("+m.codigo() + ") " + m.nome());
        });

        endereco += montadorEnderecoRequisicao
                .modeloVeiculo(validador.validarSelecionarModelo(modelo.lista()));

        json = consumoApi.obterDados(endereco);

        List<Dados> dadosAnos = converteDados.obterLista(json, Dados.class);

        String finalEndereco = endereco;
        List<Veiculo> veiculoList = dadosAnos.stream()
                .map(a -> {
                    String end = montadorEnderecoRequisicao.anoModelo(finalEndereco, a.codigo());
                    String jsonVeiculo = consumoApi.obterDados(end);
                    DadosVeiculo dadosVeiculo = converteDados.obter(jsonVeiculo, DadosVeiculo.class);
                    return new Veiculo(dadosVeiculo);
                })
                .collect(Collectors.toList());

        veiculoList.forEach(System.out::println);

//        String finalJson = json;
////        dadosAnos.forEach(a -> {
////            String jsonVeiculo = montadorEnderecoRequisicao.anoModelo(finalJson, a.codigo());
////            Veiculo veiculo = converteDados.obter(jsonVeiculo, Veiculo.class);
////
////        });
//
//        List<Veiculo> veiculoList = null;
//
//        String finalEndereco = endereco;
//        dadosAnos.forEach(a -> {
//            String jsonVeiculo = montadorEnderecoRequisicao.anoModelo(finalEndereco, a.codigo());
//            System.out.println(jsonVeiculo);
//
//            veiculoList.add(veiculo);
//        });
//
//        veiculoList.forEach(System.out::println);


    }

}
