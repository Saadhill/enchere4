package fr.eni.tp.encheres.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import fr.eni.tp.encheres.bll.*;
import fr.eni.tp.encheres.bo.*;

import java.util.List;

@Controller
@RequestMapping("/enchere")
public class EnchereController {

@GetMapping
    public String afficherAccueil(){
    return "index";
}

    @GetMapping("/profil")
    public String afficherProfil(){
        return "view-profil";
    }

}
