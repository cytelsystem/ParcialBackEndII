package com.dh.catalogservice;

import com.dh.catalogservice.model.MovieMongoDTO;
import com.dh.catalogservice.repository.LocalRepositorySeries;
import com.dh.catalogservice.repository.LocalRepositoryMovies;
import com.dh.catalogservice.model.LocalMovieDTO;
import com.dh.catalogservice.model.SerieFeinDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class CatalogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogServiceApplication.class, args);
    }



    @Bean
    public CommandLineRunner loadData(LocalRepositorySeries repository) {
        String baseUrl = "www.netflix.com/series";

        return (args) -> {
            if (!repository.findAll().isEmpty()) {
                return;
            }

            //Serie A terror
            List<SerieFeinDTO.Season.Chapter> serieASeasonAChapters = List.of(
                    new SerieFeinDTO.Season.Chapter("Chapter A", 1, baseUrl + "/terror/1/season/1/chapter/1"),
                    new SerieFeinDTO.Season.Chapter("Chapter B", 2, baseUrl + "/terror/1/season/1/chapter/2")
            );

            List<SerieFeinDTO.Season.Chapter> serieASeasonBChapters = List.of(
                    new SerieFeinDTO.Season.Chapter("Chapter A", 1, baseUrl + "/terror/1/season/2/chapter/1"),
                    new SerieFeinDTO.Season.Chapter("Chapter B", 2, baseUrl + "/terror/1/season/2/chapter/2")
            );

            List<SerieFeinDTO.Season> serieASeasons = List.of(
                    new SerieFeinDTO.Season(1, serieASeasonAChapters),
                    new SerieFeinDTO.Season(2, serieASeasonBChapters)
            );

            //Serie B comedia
            List<SerieFeinDTO.Season.Chapter> serieBSeasonAChapters = List.of(
                    new SerieFeinDTO.Season.Chapter("Chapter A", 1, baseUrl + "/comedia/1/season/1/chapter/1"),
                    new SerieFeinDTO.Season.Chapter("Chapter B", 2, baseUrl + "/comedia/1/season/1/chapter/2")
            );

            List<SerieFeinDTO.Season.Chapter> serieBSeasonBChapters = List.of(
                    new SerieFeinDTO.Season.Chapter("Chapter A", 1, baseUrl + "/comedia/1/season/2/chapter/1"),
                    new SerieFeinDTO.Season.Chapter("Chapter B", 2, baseUrl + "/comedia/1/season/2/chapter/2")
            );

            List<SerieFeinDTO.Season> serieBSeasons = List.of(
                    new SerieFeinDTO.Season(1, serieBSeasonAChapters),
                    new SerieFeinDTO.Season(2, serieBSeasonBChapters)
            );

            SerieFeinDTO serieA = new SerieFeinDTO(UUID.randomUUID().toString(),"Serie A", "terror", serieASeasons);
            SerieFeinDTO serieB = new SerieFeinDTO(UUID.randomUUID().toString(),"Serie B", "comedia", serieBSeasons);
            repository.save(serieA);
            repository.save(serieB);

        };
    }



    @Bean
    public CommandLineRunner loadDataMovieCatalogo(LocalRepositoryMovies repositoryMovies) {

        return (args) -> {
            repositoryMovies.save(new MovieMongoDTO("1", "filme", "terror", "what"));
            repositoryMovies.save(new MovieMongoDTO("2", "borboleta", "terror", "what"));
            repositoryMovies.save(new MovieMongoDTO("3", "adedonha", "terror", "what"));
            repositoryMovies.save(new MovieMongoDTO("4", "pajero", "terror", "what"));
            repositoryMovies.save(new MovieMongoDTO("5", "dakar", "acao", "what"));
            repositoryMovies.save(new MovieMongoDTO("6", "shadow", "acao", "what"));
            repositoryMovies.save(new MovieMongoDTO("7", "boné", "romance", "what"));
            repositoryMovies.save(new MovieMongoDTO("8", "xícara", "romance", "what"));
            repositoryMovies.save(new MovieMongoDTO("9", "Black Adam", "Accion", "what"));


        };
    }



}

