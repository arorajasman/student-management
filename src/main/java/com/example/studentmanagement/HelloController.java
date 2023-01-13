package com.example.studentmanagement;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// using the @RestController annotation to make the class below as rest controller
// class
@RestController
public class HelloController {

    // creating a method below to return a string

    // using the @GetMapping annotation and passing the route where we want to 
    // take the user

    // since we are using the @GetMapping so the route below will take the Get
    // request
    @GetMapping("/")
    public String hello(){
        return "Hello from spring boot";
    }

    // the method below is used to return a list of string when the user goes
    // to the "/list" route

    // using the @GetMapping() annotation to create a get method and then pass
    // the "/list" route to take the user to the list route
    @GetMapping("/list")
    public List<String> getListOfData(){
        // using the of() method from the list class and passing the items as input
        // to get the list of strings
        return List.of("Hello", "World","Springboot");
    }


}
