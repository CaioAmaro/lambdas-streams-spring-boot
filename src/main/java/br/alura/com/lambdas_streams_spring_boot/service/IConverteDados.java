package br.alura.com.lambdas_streams_spring_boot.service;

public interface IConverteDados {
   <T> T obterDados (String json, Class<T> classe);
}
