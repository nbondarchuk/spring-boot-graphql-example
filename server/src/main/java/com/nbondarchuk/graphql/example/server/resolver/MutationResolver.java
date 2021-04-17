package com.nbondarchuk.graphql.example.server.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.nbondarchuk.graphql.example.server.model.Genre;
import com.nbondarchuk.graphql.example.server.model.Movie;
import com.nbondarchuk.graphql.example.server.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class MutationResolver implements GraphQLMutationResolver {

    @Autowired
    private MovieService movieService;

    public Movie createMovie(String name, LocalDate releaseDate, List<Genre> genres) {
        return movieService.createMovie(name, releaseDate, genres);
    }
}
