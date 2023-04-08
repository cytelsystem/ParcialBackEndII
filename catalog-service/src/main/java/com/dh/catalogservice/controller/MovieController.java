package com.dh.catalogservice.controller;

import com.dh.catalogservice.clienteFein.InterfaceMovieFein;
import com.dh.catalogservice.service.ServiceMovie;
import com.dh.catalogservice.model.MovieFeinDTO;
import com.dh.catalogservice.model.LocalMovieDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class MovieController {

    @Autowired
    private InterfaceMovieFein IMovieServiceFein;
    @Autowired
    private ServiceMovie MovieService;

    @CircuitBreaker(name="movieCB",fallbackMethod = "fallback")
    @GetMapping("/{genre}")
    public ResponseEntity<List<MovieFeinDTO>> getMovieByGenre(@PathVariable String genre) {
          return ResponseEntity.ok().body(IMovieServiceFein.getMovieByGenre(genre));
    }

    @GetMapping("/local/{genre}")
    public ResponseEntity<List<LocalMovieDTO>> getMovieByGenreCatalogo(@PathVariable String genre) {
        return ResponseEntity.ok().body(MovieService.buscarPorGenero(genre));
    }


    private ResponseEntity<List<MovieFeinDTO>> fallback(@PathVariable String genre, RuntimeException e) {
          return new ResponseEntity("Base de datos no funciona", HttpStatus.OK);
    }

    @GetMapping("/genreFein/{genre}")
    public ResponseEntity<List<MovieFeinDTO>> getMovieFeinGenre(@PathVariable String genre) {
        return (ResponseEntity<List<MovieFeinDTO>>) MovieService.getMovieFeinGenre(genre);
    }




}