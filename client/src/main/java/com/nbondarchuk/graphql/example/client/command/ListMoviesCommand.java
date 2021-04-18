package com.nbondarchuk.graphql.example.client.command;

import com.apollographql.apollo.api.Response;
import com.nbondarchuk.graphql.example.client.query.MoviesQuery;
import com.nbondarchuk.graphql.example.client.reactivex.ObserverAdapter;
import com.nbondarchuk.graphql.example.client.service.MovieService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Command(
        name = "list",
        description = "Lists all movies"
)
@Component
public class ListMoviesCommand implements Runnable {

    private MovieService movieService;

    private MovieFragmentPrinter movieFragmentPrinter = new MovieFragmentPrinter();

    @Autowired
    public ListMoviesCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public void run() {
        movieService.getMovies().subscribe(new ObserverAdapter<>() {

            @Override
            public void onNext(@NotNull Response<MoviesQuery.Data> response) {
                if (response.getData() == null
                        || response.getData().movies() == null) {
                    System.out.println("No movies found.");
                    return;
                }
                response.getData().movies().forEach(movie -> {
                    System.out.printf("---%n");
                    movieFragmentPrinter.printMovie(movie.fragments().movieFragment());
                    System.out.printf("---%n");
                });
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
