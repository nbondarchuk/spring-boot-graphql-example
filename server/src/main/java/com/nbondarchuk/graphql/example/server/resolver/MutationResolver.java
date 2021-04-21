package com.nbondarchuk.graphql.example.server.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.nbondarchuk.graphql.example.server.model.Movie;
import com.nbondarchuk.graphql.example.server.model.MovieGenre;
import com.nbondarchuk.graphql.example.server.model.MovieParams;
import com.nbondarchuk.graphql.example.server.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

@Component
public class MutationResolver implements GraphQLMutationResolver {

    @Autowired
    private MovieRepository movieRepository;

    public Movie createMovie(MovieParams movieParams) {
        Movie movie = new Movie();
        movie.setName(movieParams.getName());
        movie.setDescription(movieParams.getDescription());
        movie.setReleaseDate(movieParams.getReleaseDate());
        if (movieParams.getGenres() != null) {
            movie.setGenres(movieParams.getGenres().stream().map(g -> {
                MovieGenre movieGenre = new MovieGenre();
                movieGenre.setGenre(g);
                movieGenre.setMovie(movie);
                return movieGenre;
            }).collect(toList()));
        }
        return movieRepository.save(movie);
    }
}
