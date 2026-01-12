package com.coding.springboot.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    @Value("${custom.string}")
    private String name;

    @GetMapping("/")
    public String hello() {
        System.out.println(name);
        return "Hello World!";
    }

    @GetMapping("/workout")
    public String workout(){
        return "Workout for 1 hour";
    }

    @GetMapping("/eat")
    public String eat(){
        return "Eat health food";
    }
}
