package com.nbondarchuk.graphql.example.server.model;

import lombok.Data;

@Data
public class MovieSearchParams {

    private String name;

    private Genre genre;
}
