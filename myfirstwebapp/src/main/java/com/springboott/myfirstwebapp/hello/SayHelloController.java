package com.springboott.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
    // "say-hello" -> "Hello, what are we learning today?"

    @RequestMapping("say-hello")
    @ResponseBody
    public String sayHello() {
        return "Hello, what are we learning today?";
    }

    @RequestMapping("say-hello-jsp")
    public String sayHelloJsp() {
        return "sayHello";
    }
}
