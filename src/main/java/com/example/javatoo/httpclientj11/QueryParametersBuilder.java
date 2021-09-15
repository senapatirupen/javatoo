package com.example.javatoo.httpclientj11;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class QueryParametersBuilder {
    public static void main(String[] args) {
        URI uri1 = URI.create("http://localhost:8080/books?name="
                + URLEncoder.encode("Games & Fun!", StandardCharsets.UTF_8)
                + "&no=" + URLEncoder.encode("124#442#000", StandardCharsets.UTF_8)
                + "&price=" + URLEncoder.encode("$23.99", StandardCharsets.UTF_8)
        );

        System.out.println("URI 1: " + uri1);
        URI uri2 = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(8080)
                .path("books")
                .queryParam("name", "Games & Fun!")
                .queryParam("no", "124#442#000")
                .queryParam("price", "$23.99")
                .build()
                .toUri();

        System.out.println("URI 2: " + uri2);

    }
}
