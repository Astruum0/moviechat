package com.moviechat.moviechat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class Controller {
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
                    new Message()
                            .setPlatform("ACTIONS_ON_GOOGLE")
                            .setSimpleResponses(
                                    new SimpleResponses()
                                            .setSimpleResponses(
                                                    List.of(
                                                            new SimpleResponse().setTextToSpeech(
                                                                    "Quel genre de film voulez vous voir"
                                                            )
                                                    )))
            ));
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
