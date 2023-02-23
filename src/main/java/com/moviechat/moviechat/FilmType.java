package com.moviechat.moviechat;

import lombok.Data;

import java.util.List;

@Data
public class FilmType {
    int id;
    String name;
    public static List<FilmType> all = List.of(
            new FilmType().setId(28).setName("Action"),
            new FilmType().setId(12).setName("Aventure"),
            new FilmType().setId(16).setName("Animation"),
            new FilmType().setId(35).setName("Comédie"),
            new FilmType().setId(80).setName("Crime"),
            new FilmType().setId(99).setName("Documentaire"),
            new FilmType().setId(14).setName("Fantastique"),
            new FilmType().setId(878).setName("Science-Fiction"),
            new FilmType().setId(27).setName("Horreur"),
            new FilmType().setId(10752).setName("Guerre"),
            new FilmType().setId(18).setName("Drame"));


}
