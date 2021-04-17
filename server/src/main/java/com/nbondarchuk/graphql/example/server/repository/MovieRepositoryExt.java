package com.nbondarchuk.graphql.example.server.repository;

import com.nbondarchuk.graphql.example.server.model.Movie;
import com.nbondarchuk.graphql.example.server.model.MovieSearchParams;

import java.util.List;

public interface MovieRepositoryExt {

    List<Movie> find(MovieSearchParams searchParams);
}
