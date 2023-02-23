package com.moviechat.moviechat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class Controller {
    @GetMapping("/")
    String HelloWord() {
        return "Hello World !";
    }

    @PostMapping("/chat")
    WebHookResponse Chat(@RequestBody WebHookRequest request) {
        Map<String, String> parameters = request.queryResult.parameters;

        WebHookResponse response = new WebHookResponse();

        String currentType = parameters.get("type");
        if (currentType == null || currentType == "") {
            response.setFulfillmentMessages(List.of(
                    new Message().setPlatform("ACTIONS_ON_GOOGLE").setSimpleResponses(new SimpleResponses().setSimpleResponses(List.of(
                            new SimpleResponse().setTextToSpeech("Quel genre de film voulez vous voir")
                    ))),
                    new Message().setPlatform("ACTIONS_ON_GOOGLE").setSuggestions(new Suggestions().setSuggestions(new ArrayList<Suggestion>()))));
            for (int i = 0; i < FilmType.all.size(); i++) {
                String filmType = FilmType.all.get(i).name;
                String capitalizedFilmType = filmType.substring(0, 1).toUpperCase() + filmType.substring(1);
                response.getFulfillmentMessages().get(1).getSuggestions().getSuggestions().add(
                                new Suggestion().setTitle(capitalizedFilmType));
            }
        } else {
            FilmType type = FilmType.all.stream().filter(t -> currentType.equals(t.getName())).findFirst().orElse(null);

            try {
                URL url = new URL("https://api.themoviedb.org/3/discover/movie?api_key=55148ab3bb6eb902c599051fe1fecacc&with_genres=28&language=fr-FR");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("accept", "application/json");
                InputStream responseStream = connection.getInputStream();

                ObjectMapper mapper = new ObjectMapper();
                MovieDbResponse movieResponse = mapper.readValue(responseStream, MovieDbResponse.class);



                response.setFulfillmentMessages(List.of(
                        new Message().setPlatform("ACTIONS_ON_GOOGLE").setSimpleResponses(new SimpleResponses().setSimpleResponses(List.of(
                            new SimpleResponse().setTextToSpeech(String.format("Voici les films du genre %s qui pourraient vous intéresser", type.name, type.id))))),
                        new Message().setPlatform("ACTIONS_ON_GOOGLE").setCarouselSelect(new CarouselSelect().setItems(new ArrayList<Item>()))));

                for (int i = 0; i < movieResponse.results.size(); i++) {
                    Film currentFilm = movieResponse.results.get(i);
                    response.getFulfillmentMessages().get(1).getCarouselSelect().getItems().add(
                            new Item()
                                    .setTitle(currentFilm.title)
                                    .setDescription(currentFilm.overview)
                                    .setInfo(new SelectItemInfo().setKey(Integer.toString(currentFilm.id)))
                                    .setImage(new Image().setImageUri(String.format("https://image.tmdb.org/t/p/original%s", currentFilm.poster_path)).setAccessibilityText("Poster"))
                    );
                }

            } catch (Exception e) { System.out.println(e); }
        }

        return response;

    }

}
