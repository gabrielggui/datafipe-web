package com.datafipe.datafipeweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.datafipe.datafipeweb.service.MesReferenciaService;

@Controller
public class IndexController {

    @Autowired
    private MesReferenciaService mesReferenciaService;

    @GetMapping("/")
    public String i() {
        mesReferenciaService.persistDataFromJsonString(mesReferenciaService.getJsonStringFromApi());
        return "index";
    }
}
