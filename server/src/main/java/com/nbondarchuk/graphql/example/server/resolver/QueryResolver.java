package com.nbondarchuk.graphql.example.server.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.nbondarchuk.graphql.example.server.model.Genre;
import com.nbondarchuk.graphql.example.server.model.Movie;
import com.nbondarchuk.graphql.example.server.model.MovieGenre;
import com.nbondarchuk.graphql.example.server.model.MovieSearchParams;
import com.nbondarchuk.graphql.example.server.repository.MovieRepository;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.upperCase;

@Component
@Transactional
public class QueryResolver implements GraphQLQueryResolver {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getMovies(DataFetchingEnvironment env) {
        if (genresRequested(env)) {
            return movieRepository.findAll(fetchGenres());
        }
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovie(long id, DataFetchingEnvironment env) {
        Specification<Movie> spec = (root, query, builder) -> builder.equal(root.get("id"), id);
        if (genresRequested(env)) spec = spec.and(fetchGenres());
        return movieRepository.findOne(spec);
    }

    public List<Movie> searchMovies(MovieSearchParams searchParams, DataFetchingEnvironment env) {
        Specification<Movie> spec = (root, query, builder) -> builder.and();

        if (searchParams.getName() != null) {
            spec = spec.and((root, query, builder) -> builder.like(builder.upper(root.get("name")), "%" + upperCase(searchParams.getName()) + "%"));
        }

        if (searchParams.getDescription() != null) {
            spec = spec.and((root, query, builder) -> builder.like(builder.upper(root.get("description")), "%" + upperCase(searchParams.getDescription()) + "%"));
        }

        if (searchParams.getGenre() != null || genresRequested(env)) {
            spec = spec.and(fetchGenres(searchParams.getGenre()));
        }

        return movieRepository.findAll(spec);
    }

    private Specification<Movie> fetchGenres() {
        return fetchGenres(null);
    }

    private Specification<Movie> fetchGenres(Genre genre) {
        return (root, query, builder) -> {
            query.distinct(true);
            Join<Movie, MovieGenre> join = (Join<Movie, MovieGenre>) root.<Movie, MovieGenre>fetch("genres", JoinType.LEFT);
            if (genre != null) {
                return builder.equal(join.get("genre"), genre);
            }
            return join.getOn();
        };
    }

    private boolean genresRequested(DataFetchingEnvironment env) {
        DataFetchingFieldSelectionSet selectionSet = env.getSelectionSet();
        return selectionSet.get().keySet().stream().anyMatch(k -> k.toLowerCase().contains("genres"));
    }
}
