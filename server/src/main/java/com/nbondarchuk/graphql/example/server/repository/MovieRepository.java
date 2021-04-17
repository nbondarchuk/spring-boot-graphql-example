package com.nbondarchuk.graphql.example.server.repository;

import com.nbondarchuk.graphql.example.server.model.Genre;
import com.nbondarchuk.graphql.example.server.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT mg.movie FROM MovieGenre mg where mg.genre = :genre")
    List<Movie> findByGenre(@Param("genre") Genre genre);
}
