package com.moviechat.moviechat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    @GetMapping("/hello")
    String HelloWord() {
        return "Hello World !";
    }

    @PostMapping("/chat")
    WebHookResponse Chat(@RequestBody WebHookRequest response) {
        return new WebHookResponse()
                .setFulfillmentMessages(List.of(
                        new Message()
                                .setPlatform("ACTIONS_ON_GOOGLE")
                                .setSimpleResponses(
                                        new SimpleResponses()
                                                .setSimpleResponses(
                                                        List.of(
                                                                new SimpleResponse().setTextToSpeech(
                                                                        response.queryResult.action + " " + response.queryResult.parameters.toString()
                                                                )
                                                        )))
                ));
    }

}
