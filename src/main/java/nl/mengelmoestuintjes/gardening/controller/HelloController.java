package nl.mengelmoestuintjes.gardening.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // de value specificeerd de endpoint
    @GetMapping(value = "/hello")
    public String sayHello() {
        return "Hello World";
    }

    @GetMapping(value = "/bye")
    public String sayGoodbye() {
        return "Bye Bye";
    }
}
