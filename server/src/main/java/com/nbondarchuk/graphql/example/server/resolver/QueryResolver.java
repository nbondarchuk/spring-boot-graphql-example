package com.nbondarchuk.graphql.example.server.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.nbondarchuk.graphql.example.server.model.Movie;
import com.nbondarchuk.graphql.example.server.model.MovieSearchParams;
import com.nbondarchuk.graphql.example.server.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    @Autowired
    private MovieService movieService;

    public List<Movie> getMovies() {
        return movieService.getMovies();
    }

    public Optional<Movie> getMovie(long id) {
        return movieService.getMovie(id);
    }

    public List<Movie> searchMovies(MovieSearchParams searchParams) {
        return movieService.searchMovies(searchParams);
    }
}
