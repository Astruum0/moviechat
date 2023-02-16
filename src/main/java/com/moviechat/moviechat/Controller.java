package com.moviechat.moviechat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/hello")
    String HelloWord() {
        return "Hello World !";
    }

    @PostMapping("/chat")
    String Chat(@RequestBody Object res) {
        return res.toString();
    }

}
