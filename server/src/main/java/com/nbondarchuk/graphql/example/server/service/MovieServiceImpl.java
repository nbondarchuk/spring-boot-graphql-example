package com.nbondarchuk.graphql.example.server.service;

import com.nbondarchuk.graphql.example.server.model.Genre;
import com.nbondarchuk.graphql.example.server.model.Movie;
import com.nbondarchuk.graphql.example.server.model.MovieGenre;
import com.nbondarchuk.graphql.example.server.model.MovieSearchParams;
import com.nbondarchuk.graphql.example.server.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Component
@Transactional(readOnly = true)
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> getMovie(long id) {
        return movieRepository.findById(id);
    }

    @Override
    public List<Movie> searchMovies(MovieSearchParams searchParams) {
        return movieRepository.findByGenre(searchParams.getGenre());
    }

    @Transactional
    public Movie createMovie(String name, LocalDate releaseDate, List<Genre> genres) {
        Movie movie = new Movie();
        movie.setName(name);
        movie.setReleaseDate(releaseDate);
        if (genres != null) {
            movie.setGenres(genres.stream().map(g -> {
                MovieGenre movieGenre = new MovieGenre();
                movieGenre.setGenre(g);
                movieGenre.setMovie(movie);
                return movieGenre;
            }).collect(toList()));
        }
        return movieRepository.save(movie);
    }
}
