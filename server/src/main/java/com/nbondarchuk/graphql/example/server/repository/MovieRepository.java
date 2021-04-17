package com.nbondarchuk.graphql.example.server.repository;

import com.nbondarchuk.graphql.example.server.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>, MovieRepositoryExt {
}
