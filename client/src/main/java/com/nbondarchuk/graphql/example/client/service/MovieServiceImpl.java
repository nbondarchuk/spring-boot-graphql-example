package com.nbondarchuk.graphql.example.client.service;

import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.ApolloQueryCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.rx2.Rx2Apollo;
import com.nbondarchuk.graphql.example.client.query.MovieQuery;
import com.nbondarchuk.graphql.example.client.query.MovieQuery.Movie;
import com.nbondarchuk.graphql.example.client.query.MoviesQuery;
import com.nbondarchuk.graphql.example.client.query.SearchMoviesQuery;
import com.nbondarchuk.graphql.example.client.type.MovieSearchParams;
import io.reactivex.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MovieServiceImpl implements MovieService {

    private ApolloClient apolloClient;

    @Autowired
    public MovieServiceImpl(ApolloClient apolloClient) {
        this.apolloClient = apolloClient;
    }

    @Override
    public Optional<Movie> getMovie(long id) {
        ApolloQueryCall<MovieQuery.Data> query = apolloClient.query(MovieQuery.builder()
                .id(String.valueOf(id))
                .build());
        return Optional.empty();
    }

    @Override
    public Observable<Response<MoviesQuery.Data>> getMovies() {
        return Rx2Apollo.from(apolloClient.query(MoviesQuery.builder().build()));
    }

    @Override
    public Observable<Response<SearchMoviesQuery.Data>> searchMovies(MovieSearchParams searchParams) {
        return Rx2Apollo.from(apolloClient.query(SearchMoviesQuery.builder()
                .searchParams(searchParams)
                .build()));
    }
}
