package com.elearning.elearning.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/userss")
public class Home {


    @RequestMapping("/yuy")
    public String helloGFG()
    {
        return "Hello Welcome";
    }
    @RequestMapping("/welcome")
    public String homeAS()
    {
        return "Hello Pradeep As";
    }
}
