package com.nbondarchuk.graphql.example.server.model;

import lombok.Data;

@Data
public class MovieSearchParams {

    private String name;

    private String description;

    private Genre genre;
}
