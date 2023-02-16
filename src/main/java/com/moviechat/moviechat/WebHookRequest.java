package com.moviechat.moviechat;

import java.util.Map;

public class WebHookRequest {
    QueryResult queryResult;
}

class QueryResult {
    String action;
    Map<String, String> parameters;
}