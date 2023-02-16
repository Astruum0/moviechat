package com.moviechat.moviechat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {
    List<String> filmTypes = List.of("science-fiction", "fantaisie", "romance");
    @GetMapping("/hello")
    String HelloWord() {
        return "Hello World !";
    }

    @PostMapping("/chat")
    WebHookResponse Chat(@RequestBody WebHookRequest request) {
        Map<String, String> parameters = request.queryResult.parameters;

        WebHookResponse response = new WebHookResponse();

        String filmType = parameters.get("type");
        if (filmType == null || filmType == "") {
            response.setFulfillmentMessages(List.of(
                    new Message().setPlatform("ACTIONS_ON_GOOGLE").setSimpleResponses(new SimpleResponses().setSimpleResponses(List.of(
                            new SimpleResponse().setTextToSpeech("Quel genre de film voulez vous voir")
                    ))),
                    new Message().setPlatform("ACTIONS_ON_GOOGLE").setCarouselSelect(new CarouselSelect().setItems(new ArrayList<Item>()))));
            for (int i = 0; i < filmTypes.size(); i++) {
                String currentType = filmTypes.get(i);
                String capitalizedFilmType = currentType.substring(0, 1).toUpperCase() + currentType.substring(1);
                response.getFulfillmentMessages().get(1).getCarouselSelect().getItems().add(
                                new Item()
                                        .setTitle(capitalizedFilmType)
                                        .setSelectItemInfo(new SelectItemInfo().setKey(filmTypes.get(i))));
            }
        } else {
            response.setFulfillmentMessages(List.of(
                    new Message()
                            .setPlatform("ACTIONS_ON_GOOGLE")
                            .setSimpleResponses(
                                    new SimpleResponses()
                                            .setSimpleResponses(
                                                    List.of(
                                                            new SimpleResponse().setTextToSpeech(
                                                                    String.format("Voici les films du genre %s qui pourraient vous intéresser", filmType)
                                                            )
                                                    )))
            ));
        }

        return response;

    }

}
