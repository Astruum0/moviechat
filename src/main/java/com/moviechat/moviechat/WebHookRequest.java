package com.moviechat.moviechat;

public class WebHookRequest {
    QueryResult queryResult;
}

class QueryResult {
    String action;
    Object parameters;
}