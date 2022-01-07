package nl.mengelmoestuintjes.gardening.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class ApiController {

    @GetMapping
    public String welcome() {
        return "Welkom bij Mengelmoestuintjes API";
    }

}
