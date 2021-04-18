package com.nbondarchuk.graphql.example.client.service;

import com.apollographql.apollo.api.Response;
import com.nbondarchuk.graphql.example.client.query.MovieQuery;
import com.nbondarchuk.graphql.example.client.query.MoviesQuery;
import com.nbondarchuk.graphql.example.client.query.SearchMoviesQuery;
import com.nbondarchuk.graphql.example.client.type.MovieSearchParams;
import io.reactivex.Observable;

public interface MovieService {

    Observable<Response<MovieQuery.Data>> getMovie(long id);

    Observable<Response<MoviesQuery.Data>> getMovies();

    Observable<Response<SearchMoviesQuery.Data>> searchMovies(MovieSearchParams searchParams);
}
