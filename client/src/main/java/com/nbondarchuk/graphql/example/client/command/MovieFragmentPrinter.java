package com.nbondarchuk.graphql.example.client.command;

import com.nbondarchuk.graphql.example.client.fragment.MovieFragment;
import com.nbondarchuk.graphql.example.client.type.Genre;

import java.util.List;

import static java.util.stream.Collectors.joining;
import static org.apache.commons.collections.CollectionUtils.isEmpty;

public class MovieFragmentPrinter {

    public void printMovie(MovieFragment movie) {
        System.out.printf("---%n");
        doPrintMovie(movie);
        System.out.printf("---%n");
    }

    private void doPrintMovie(MovieFragment movie) {
        System.out.format("%s%n", movie.name());
        System.out.format("Released\t%s%n", movie.releaseDate() != null ? movie.releaseDate() : "Unknown");
        System.out.printf("Genre\t\t%s%n", genresToString(movie.movieGenres()));
    }

    private String genresToString(List<MovieFragment.MovieGenre> genres) {
        if (isEmpty(genres)) {
            return "Unknown";
        }
        return genres.stream()
                .map(MovieFragment.MovieGenre::genre)
                .map(Genre::name)
                .collect(joining(","));
    }
}
