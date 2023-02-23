package com.moviechat.moviechat;

import lombok.Data;

import java.util.List;

@Data
public class FilmType {
    int id;
    String name;
    public static List<FilmType> all = List.of(
            new FilmType().setId(28).setName("Action"),
            new FilmType().setId(14).setName("Fantastique"),
            new FilmType().setId(878).setName("Science-Fiction"),
            new FilmType().setId(18).setName("Drame"));
}
