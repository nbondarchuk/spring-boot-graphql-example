package com.nbondarchuk.graphql.example.server.repository;

import com.nbondarchuk.graphql.example.server.model.Movie;
import com.nbondarchuk.graphql.example.server.model.MovieGenre;
import com.nbondarchuk.graphql.example.server.model.MovieSearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.upperCase;

@Repository
public class MovieRepositoryExtImpl implements MovieRepositoryExt {

    @Autowired
    private EntityManager em;

    @Override
    public List<Movie> find(MovieSearchParams searchParams) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Movie> cq = cb.createQuery(Movie.class);

        Root<Movie> movie = cq.from(Movie.class);
        List<Predicate> predicates = new ArrayList<>();
        if (searchParams.getName() != null) {
            predicates.add(cb.like(cb.upper(movie.get("name")), "%" + upperCase(searchParams.getName()) + "%"));
        }

        if (searchParams.getDescription() != null) {
            predicates.add(cb.like(cb.upper(movie.get("description")), "%" + upperCase(searchParams.getDescription()) + "%"));
        }

        if (searchParams.getGenre() != null) {
            Join<Movie, MovieGenre> genres = movie.join("genres");
            predicates.add(cb.equal(genres.get("genre"), searchParams.getGenre()));
        }
        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }
}
