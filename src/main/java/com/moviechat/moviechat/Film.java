package com.moviechat.moviechat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Film {
    int id;
    String title;
    String overview;
    String release_date;

    String poster_path;
    int vote_average;


}
