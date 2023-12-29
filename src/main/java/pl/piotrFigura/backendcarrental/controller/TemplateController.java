package pl.piotrFigura.backendcarrental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
class TemplateController {

    @GetMapping("login")
    String getLoginView(){
        return "login";
    }
    @GetMapping("cars")
    String getCars(){
        return "cars";
    }
}
