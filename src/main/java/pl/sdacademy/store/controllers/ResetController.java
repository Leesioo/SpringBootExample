package pl.sdacademy.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sdacademy.store.components.MyCounter;

@Controller
public class ResetController {

    @Autowired
    private MyCounter myCounter;

    @GetMapping("/reset")
    public String showReset(ModelMap modelMap) {
        modelMap.addAttribute("counter", myCounter.setCounter(0));
        return "reset";
    }


}
