package com.nbondarchuk.graphql.example.server.service;

import com.nbondarchuk.graphql.example.server.model.Genre;
import com.nbondarchuk.graphql.example.server.model.Movie;
import com.nbondarchuk.graphql.example.server.model.MovieSearchParams;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieService {

    List<Movie> getMovies();

    Optional<Movie> getMovie(long id);

    List<Movie> searchMovies(MovieSearchParams searchParams);

    Movie createMovie(String name, LocalDate date, List<Genre> genres);
}
