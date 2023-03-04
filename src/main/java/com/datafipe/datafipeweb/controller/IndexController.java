package com.datafipe.datafipeweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.datafipe.datafipeweb.service.MarcaService;

@Controller
public class IndexController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping("/")
    public String i() {
        marcaService.salvarMarcas();
        return "index";
    }
}
