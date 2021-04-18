package com.nbondarchuk.graphql.example.client.command;

import com.nbondarchuk.graphql.example.client.fragment.MovieFragment;
import com.nbondarchuk.graphql.example.client.type.Genre;

import java.util.List;

import static java.util.stream.Collectors.joining;
import static org.apache.commons.collections.CollectionUtils.isEmpty;

public class MovieFragmentPrinter {

    private static final String UNKNOWN = "Unknown";

    public void printMovie(MovieFragment movie) {
        System.out.format("Id\t\t\t%s%n", movie.id());
        System.out.format("Name\t\t%s%n", movie.name());
        System.out.format("Released\t%s%n", movie.releaseDate() != null ? movie.releaseDate() : UNKNOWN);
        System.out.printf("Genre\t\t%s%n", genresToString(movie.movieGenres()));
    }

    private String genresToString(List<MovieFragment.MovieGenre> genres) {
        if (isEmpty(genres)) {
            return UNKNOWN;
        }
        return genres.stream()
                .map(MovieFragment.MovieGenre::genre)
                .map(Genre::name)
                .collect(joining(","));
    }
}
