package pl.sdacademy.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sdacademy.store.components.MyCounter;

@Controller
public class SetCounterController {

    @Autowired
    private MyCounter myCounter;

    @GetMapping("/setcounter")
    public String showNewValue(ModelMap modelMap) {
        modelMap.addAttribute("counter", myCounter.getCounter());
        return "setcounter";
    }


}
