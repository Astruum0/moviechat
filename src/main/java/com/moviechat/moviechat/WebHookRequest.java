package com.moviechat.moviechat;

import lombok.Data;

import java.util.Map;

@Data
public class WebHookRequest {
    QueryResult queryResult;
}

@Data
class QueryResult {
    String action;
    Map<String, String> parameters;
}