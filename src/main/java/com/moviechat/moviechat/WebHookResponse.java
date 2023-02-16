package com.moviechat.moviechat;

import lombok.Data;

import java.util.List;
@Data
public class WebHookResponse {
    private List<Message> fulfillmentMessages;
}

@Data
class Message {
    String platform;
    SimpleResponses simpleResponses;
}
@Data
class SimpleResponses {
    List<SimpleResponse> simpleResponses;
}

@Data
class SimpleResponse {
    String textToSpeech;
}

