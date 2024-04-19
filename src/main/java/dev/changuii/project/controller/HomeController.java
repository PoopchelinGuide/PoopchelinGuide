package dev.changuii.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {


    @GetMapping("/message")
    @ResponseBody
    public Map message(
            @RequestParam(value = "value", required = false) String x
    ){
        HashMap<String, String> value = new HashMap<>();
        value.put("value", "류세민" + x);
        return value;
    }


    @RequestMapping("/")
    public String homepage(
            @RequestParam("name") String name,
            Model model){

        model.addAttribute("name", name+"의 홈페이지");
        model.addAttribute("title", "My Home Page");
        return "index";
    }
}
