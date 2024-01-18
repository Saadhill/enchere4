package fr.eni.tp.encheres.controller;

import fr.eni.tp.encheres.bll.UtilisateurService;
import fr.eni.tp.encheres.bo.Utilisateur;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;


@Controller
@RequestMapping("/login")
public class LoginController {
    private UtilisateurService utilisateurService;


    public LoginController(UtilisateurService utilisateurService) {
        super();
        this.utilisateurService = utilisateurService;
    }

    @GetMapping()
    public String afficherLogin() {
        return "login";
    }

  /*  @GetMapping("/creercompte")
    public String afficherCreercompte(@ModelAttribute Utilisateur utilisateur) {
        return "/creercompte";
    }*/


    @GetMapping("/inscription")
    public String afficherCreerCompte(Model model) {
        System.out.println("xxxxxxxxx");
        model.addAttribute("utilisateur", new Utilisateur());
        return "creercompte";
    }


    @PostMapping("/register")
    public String register(@Valid @ModelAttribute Utilisateur utilisateur, BindingResult validationResult

    ) {
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");


        utilisateurService.createUtilisateur(utilisateur);
        return "redirect:/login";


    }



  /*@PostMapping("/register")
    public String createUtilisateur(@ModelAttribute("utilisateur")Utilisateur utilisateur) {
        System.out.println("utilisateur : " + utilisateur);
        this.utilisateurService.createUtilisateur(utilisateur);
        return "redirect:/login";
    }*/

    // la méthode POST pour s'inscrire
   /* @PostMapping("/signin")
    public String sinscrire(
            @RequestParam("pseudo") String pseudo,
            @RequestParam("prenom") String prenom,
            @RequestParam("nom") String nom,
            @RequestParam("email") String email,
            @RequestParam("phone") String telephone,
            @RequestParam("rue") String rue,
            @RequestParam("code") String codePostal,
            @RequestParam("ville") String ville,
            @RequestParam("mdp") String motDePasse) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPseudo(pseudo);
        utilisateur.setPrenom(prenom);
        utilisateur.setNom(nom);
        utilisateur.setEmail(email);
        utilisateur.setTelephone(telephone);
        utilisateur.setRue(rue);
        utilisateur.setCodePostal(codePostal);
        utilisateur.setVille(ville);
        utilisateur.setMotDePasse(motDePasse);
        this.utilisateurService.createUtilisateur(utilisateur);
        System.out.println(utilisateur);
        return "redirect:/";
    }*/
}
