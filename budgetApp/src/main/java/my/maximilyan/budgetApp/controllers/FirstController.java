package my.maximilyan.budgetApp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
    @GetMapping
    public String helloWeb() {
        return "Проклюнулся великий разработчик";
    }

    @GetMapping("path/to/page")
    public String helloWeb2(@RequestParam String page) {
        return "Страница: " + page;
    }
}
