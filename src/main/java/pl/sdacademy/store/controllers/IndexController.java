package pl.sdacademy.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sdacademy.store.components.MyCounter;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private MyCounter myCounter;

    @RequestMapping("/index")
    public String showIndex(String firstName, ModelMap modelMap) {
        modelMap.addAttribute("counter", myCounter.increment());
        return "index";
    }
}
