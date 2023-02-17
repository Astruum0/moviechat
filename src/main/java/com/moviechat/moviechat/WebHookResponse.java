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
    Suggestions suggestions;
    CarouselSelect carouselSelect;

}
@Data
class SimpleResponses {
    List<SimpleResponse> simpleResponses;
}
@Data
class SimpleResponse {
    String textToSpeech;
}

@Data
class Suggestions {
    List<Suggestion> suggestions;
}
@Data
class Suggestion {
    String title;
}

@Data
class CarouselSelect {
    List<Item> items;
}

@Data
class Item {
    String title;
    SelectItemInfo info;
    String description;
}

@Data
class SelectItemInfo {
    String key;
}
