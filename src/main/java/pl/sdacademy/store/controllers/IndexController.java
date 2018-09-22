package pl.sdacademy.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sdacademy.store.services.CustomerService;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping
    public String showIndex(ModelMap modelMap) {
        modelMap.addAttribute("customers", customerService.findAll());
        return "index";
    }
}
