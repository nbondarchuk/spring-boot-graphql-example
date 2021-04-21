package com.nbondarchuk.graphql.example.server.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MovieParams {

    private String name;

    private String description;

    private LocalDate releaseDate;

    private List<Genre> genres;
}
