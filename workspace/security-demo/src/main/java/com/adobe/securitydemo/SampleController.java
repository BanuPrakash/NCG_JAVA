package com.adobe.securitydemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class SampleController {

    @GetMapping()
    public String sayHello() {
        return  "Hello User!!!";
    }

    @GetMapping("/admin")
    public String sayHelloAdmin() {
        return  "Hello Admin!!!";
    }

}
