package com.nbondarchuk.graphql.example.client.command;

import com.apollographql.apollo.api.Response;
import com.nbondarchuk.graphql.example.client.query.SearchMoviesQuery;
import com.nbondarchuk.graphql.example.client.reactivex.ObserverAdapter;
import com.nbondarchuk.graphql.example.client.service.MovieService;
import com.nbondarchuk.graphql.example.client.type.Genre;
import com.nbondarchuk.graphql.example.client.type.MovieSearchParams;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;
import picocli.CommandLine.Option;

@CommandLine.Command(
        name = "search",
        description = "Searches movies by parameters"
)
@Component
public class SearchMoviesCommand implements Runnable {

    @Option(names = {"-g", "--genre"}, description = "movie genre")
    private Genre genre;

    @Option(names = {"-n", "--name"}, description = "movie name")
    private String name;

    private MovieService movieService;

    private MovieFragmentPrinter movieFragmentPrinter = new MovieFragmentPrinter();

    @Autowired
    public SearchMoviesCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public void run() {
        MovieSearchParams searchParams = MovieSearchParams.builder()
                .name(name)
                .genre(genre)
                .build();
        movieService.searchMovies(searchParams).subscribe(new ObserverAdapter<>() {

            @Override
            public void onNext(@NotNull Response<SearchMoviesQuery.Data> response) {
                if (response.getData() == null
                        || response.getData().searchMovies() == null) {
                    System.out.println("No movies found.");
                    return;
                }
                response.getData().searchMovies().forEach(movie -> {
                    movieFragmentPrinter.printMovie(movie.fragments().movieFragment());
                });
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
