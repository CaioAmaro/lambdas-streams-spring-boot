package br.alura.com.lambdas_streams_spring_boot.desafio.service;

import br.alura.com.lambdas_streams_spring_boot.desafio.models.Dados;

import java.util.*;
import java.util.stream.Collectors;

public class Validador {
    private Scanner input = new Scanner(System.in);

    public int validarTipoVeiculo(){

        boolean valido = false;
        int tipoVeiculo = -1;

        while(!valido){
            try{
                System.out.print("Digite sua escolha: ");
                tipoVeiculo = input.nextInt();

                if(tipoVeiculo < 1 || tipoVeiculo > 3){
                    throw new IllegalArgumentException("Opção selecionada Invalida.");
                }

                valido = true;

            }catch (IllegalArgumentException e){
                System.out.println("Erro: " + e.getMessage());
            }catch (InputMismatchException e){
                System.out.println("Erro: Opção selecionada Invalida.");
                input.nextLine(); //limpa buffer;
            }
        }

        return tipoVeiculo;
    }

    public int validarMarcaVeiculo(List<Dados> listaMarcas){

        boolean valido = false;
        int marcaVeiculo = -1;

        while(!valido){
            try{
                System.out.print("Digite sua escolha: ");
                marcaVeiculo = input.nextInt();


                String marcaVeiculoString = Integer.toString(marcaVeiculo);
                Optional<String> filtro = listaMarcas.stream()
                        .filter(m -> m.codigo().equals(marcaVeiculoString))
                        .map(Dados::codigo)
                        .findFirst();

                if(filtro.isEmpty()){
                    throw new NoSuchElementException("Opção selecionada Invalida.");
                }

                valido = true;

            }catch (IllegalArgumentException e){
                System.out.println("Erro: " + e.getMessage());
            }catch (InputMismatchException e){
                System.out.println("Erro: Opção selecionada Invalida.");
                input.nextLine(); //limpa buffer;
            }
        }

        return marcaVeiculo;
    }

    public List<Dados> validarPesquisaModelo(List<Dados> dadosModeloLista){

        boolean valido = false;
        String trecho = "";
        List<Dados> resposta = null;
        input.nextLine(); // Limpa Buffer

        while(!valido){
            try{
                System.out.println("Digite um trecho para pesquisa: ");
                trecho = input.nextLine();


                String finalTrecho = trecho.toUpperCase();
                resposta = dadosModeloLista.stream()
                        .filter(m -> m.nome().toUpperCase().contains(finalTrecho))
                        .collect(Collectors.toList());

               if(resposta.isEmpty()){
                   throw new NoSuchElementException("Não houve resultado de sua busca, tente novamente.");
               }

                valido = true;

            }catch (IllegalArgumentException e){
                System.out.println("Erro: " + e.getMessage());
            }catch (InputMismatchException e){
                System.out.println("Erro: Opção selecionada Invalida.");
                input.nextLine(); //limpa buffer;
            }catch (NoSuchElementException e){
                System.out.println(e.getMessage());
            }
        }

        return resposta;
    }

    public int validarSelecionarModelo(List<Dados> dadosModeloLista){
        boolean valido = false;
        int modeloVeiculo = -1;

        while(!valido){
            try{
                System.out.println("Digite a sua Escolha: ");
                modeloVeiculo = input.nextInt();

                String modeloVeiculoString = Integer.toString(modeloVeiculo);
                Optional<String> filtro = dadosModeloLista.stream()
                        .filter(m -> m.codigo().equals(modeloVeiculoString))
                        .map(Dados::codigo)
                        .findFirst();

                if(filtro.isEmpty()){
                    throw new NoSuchElementException("Opção selecionada Invalida.");
                }

                valido = true;

            }catch (IllegalArgumentException e){
                System.out.println("Erro: " + e.getMessage());
            }catch (InputMismatchException e){
                System.out.println("Erro: Opção selecionada Invalida.");
                input.nextLine(); //limpa buffer;
            }catch (NoSuchElementException e){
                System.out.println(e.getMessage());
            }
        }

        return modeloVeiculo;
    }
}
