package com.nbondarchuk.graphql.example.client.command;

import com.apollographql.apollo.api.Response;
import com.nbondarchuk.graphql.example.client.fragment.MovieFragment;
import com.nbondarchuk.graphql.example.client.query.MovieQuery;
import com.nbondarchuk.graphql.example.client.reactivex.ObserverAdapter;
import com.nbondarchuk.graphql.example.client.service.MovieService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

@CommandLine.Command(
        name = "info",
        description = "Shows full movie info"
)
@Component
public class ShowMovieInfoCommand implements Runnable {

    @CommandLine.Option(names = {"--id"}, description = "movie ID", required = true)
    private Long id;

    private MovieService movieService;

    private MovieFragmentPrinter movieFragmentPrinter = new MovieFragmentPrinter();

    public ShowMovieInfoCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public void run() {
        movieService.getMovie(id).subscribe(new ObserverAdapter<>() {

            @Override
            public void onNext(@NotNull Response<MovieQuery.Data> response) {
                if (response.getData() == null
                        || response.getData().movie() == null) {
                    System.out.println("No movie found.");
                    return;
                }

                MovieFragment movieFragment = response.getData().movie().fragments().movieFragment();
                movieFragmentPrinter.printMovie(movieFragment);
                System.out.printf("%s", response.getData().movie().description());
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
