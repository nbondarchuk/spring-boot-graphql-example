package com.nbondarchuk.graphql.example.client;

import com.nbondarchuk.graphql.example.client.command.ListMoviesCommand;
import com.nbondarchuk.graphql.example.client.command.MovieDatabase;
import com.nbondarchuk.graphql.example.client.command.SearchMoviesCommand;
import com.nbondarchuk.graphql.example.client.command.ShowMovieInfoCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;

@SpringBootApplication
public class GraphQLClient implements CommandLineRunner {

    @Autowired
    private ListMoviesCommand listMoviesCommand;

    @Autowired
    private SearchMoviesCommand searchMoviesCommand;

    @Autowired
    private ShowMovieInfoCommand showMovieInfoCommand;

    public static void main(String[] args) {
        SpringApplication.run(GraphQLClient.class, args);
    }

    @Override
    public void run(String... args) {
        CommandLine commandLine = new CommandLine(new MovieDatabase());
        commandLine.addSubcommand(listMoviesCommand);
        commandLine.addSubcommand(searchMoviesCommand);
        commandLine.addSubcommand(showMovieInfoCommand);
        commandLine.addSubcommand(new CommandLine.HelpCommand());

        System.exit(commandLine.execute(args));
    }
}
