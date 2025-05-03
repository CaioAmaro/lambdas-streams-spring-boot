package br.alura.com.lambdas_streams_spring_boot.main;

import br.alura.com.lambdas_streams_spring_boot.model.DadosEpisodio;
import br.alura.com.lambdas_streams_spring_boot.model.DadosSerie;
import br.alura.com.lambdas_streams_spring_boot.model.DadosTemporada;
import br.alura.com.lambdas_streams_spring_boot.model.Episodio;
import br.alura.com.lambdas_streams_spring_boot.service.ConsumoApi;
import br.alura.com.lambdas_streams_spring_boot.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private Scanner input = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=afd46234";

    public void exibeMenu(){
        System.out.print("Digite nome da série: ");
        var nomeSerie = input.nextLine();
        var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ","+") +API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for(int i = 1; i <= dados.totalTemporadas(); i++){
            json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ","+") + "&season="+ i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }

        temporadas.forEach(System.out::println);

//        for(int i = 0; i  < dados.totalTemporadas(); i++){
//            List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
//
//            System.out.println("Temporada: " + (i+1));
//            for(int j = 0; j < episodiosTemporada.size(); j++){
//                System.out.println(episodiosTemporada.get(j).titulo());
//            }
//        }

//        temporadas.forEach(t -> {
//            System.out.println("Temporada: " + t.numero());
//            t.episodios().forEach(e -> System.out.println(e.titulo()));
//        });

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());


//        dadosEpisodios.stream()
//                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
//                .peek(e -> System.out.println("Primeiro Filtro (N/A) " + e))
//                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
//                .peek(e -> System.out.println("Ordenação " + e))
//                .limit(10)
//                .peek(e -> System.out.println("Limite " + e))
//                .map(e -> e.titulo().toUpperCase())
//                .peek(e -> System.out.println("Mapeamento " + e))
//                .forEach(System.out::println);

        List<Episodio> listaEpisodio = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d)))
                .collect(Collectors.toList());

        System.out.println("Digite um trecho do episodio que você deseja: ");
            var trechoEpisodio = input.nextLine();
            Optional<Episodio> episodioBuscado = listaEpisodio.stream()
                    .filter(e -> e.getTitulo().toUpperCase().contains(trechoEpisodio.toUpperCase()))
                    .findFirst();

            if(episodioBuscado.isPresent()){
                System.out.println("Episodio encontrado!");
                System.out.println("Temporada: " + episodioBuscado.get().getTemporada());
            }else{
                System.out.println("Episodio não encontrado");
            }

            Map<Integer, Double> avaliacoesPorTemporada = listaEpisodio.stream()
                    .filter(e -> e.getAvaliacao() > 0.0)
                    .collect(Collectors.groupingBy(Episodio::getTemporada, Collectors.averagingDouble(Episodio::getAvaliacao)));

        System.out.println(avaliacoesPorTemporada);


        DoubleSummaryStatistics est = listaEpisodio.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));

        System.out.println("Média: " + est.getAverage());
        System.out.println("Melhor Episodio: " + est.getMax());
        System.out.println("Pior Episodio: " + est.getMin());
        System.out.println("Quantidade: " + est.getCount());


//        listaEpisodio.forEach(System.out::println);
//
//        System.out.println("Apartir de que ano você deseja ver os episodios");
//        var ano = input.nextInt();
//        input.nextLine();
//
//        LocalDate dataBusca = LocalDate.of(ano,1,1);
//
//
//        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        listaEpisodio.stream()
//                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
//                .forEach(e -> System.out.println(
//                    "Temporada: " + e.getTemporada() +
//                            " Episódio: " + e.getNumeroEpisodio()  +
//                            " Data de Lançamento: " + e.getDataLancamento().format(formatador)
//                ));

    }
}
