package pl.sdacademy.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sdacademy.store.components.MyCounter;

import static java.lang.Integer.parseInt;

@Controller
public class NewCounterController {

    @Autowired
    private MyCounter myCounter;

    @GetMapping("/newcounter")
    public String showNewValue(String newValue, ModelMap modelMap) {
        Integer newCounter = parseInt(newValue);
        modelMap.addAttribute("counter", myCounter.setCounter(newCounter));
        return "index";
    }


}
