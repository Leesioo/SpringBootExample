package pl.sdacademy.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sdacademy.store.components.MyCounter;

@Controller
public class HelloController {

    @Autowired
    private MyCounter myCounter;

    @GetMapping("/hello")
    public String showHello(String firstName, ModelMap modelMap) {
        modelMap.addAttribute("firstName", firstName);
        modelMap.addAttribute("counter", myCounter.increment());
        return "hello";
    }


}
