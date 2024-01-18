package fr.eni.tp.encheres.bll.impl;

import fr.eni.tp.encheres.dal.UtilisateurDAO;
import fr.eni.tp.encheres.bll.UtilisateurService;
import fr.eni.tp.encheres.bo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurDAO utilisateurDAO;

    @Autowired
    PasswordEncoder passw;

    public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO) {
        super();
        this.utilisateurDAO = utilisateurDAO;

    }

    @Override
    public void createUtilisateur(Utilisateur utilisateur) {

        if (utilisateur.getMotDePasse() != null) {

//            utilisateur.setMotDePasse("{bcrypt}" + passw.encode(utilisateur.getMotDePasse()));
            utilisateur.setMotDePasse(passw.encode(utilisateur.getMotDePasse()));

        }
        this.utilisateurDAO.insert(utilisateur);


    }

    @Override
    public Boolean isPseudoUnique(String pseudo) {
        return this.utilisateurDAO.isPseudoUnique(pseudo);
    }

    @Override
    public Boolean isMailUnique(String email) {
        return this.utilisateurDAO.isMailUnique(email);
    }


    @Override
    public List<Utilisateur> findAllUtilisateurs() {

        return this.utilisateurDAO.findAll();


    }

    @Override
    public Utilisateur findUtilisateurById(Integer id) {

        Utilisateur utilisateur = this.utilisateurDAO.read(id);

        return utilisateur;
    }

    @Override
    public Utilisateur findUserByPseudo(String pseudo) {
        Utilisateur utilisateur = this.utilisateurDAO.readSpeudo(pseudo);

        return utilisateur;
    }


    @Override
    public void updateUtilisateur(Utilisateur utilisateur) {

        this.utilisateurDAO.update(utilisateur);


    }

    @Override
    public void updateCredit(int valeur, Utilisateur utilisateur) {
        //TODO

    }

    @Override
    public void deleteUtilisateur(Integer no_utilisateur) {

        this.utilisateurDAO.delete(no_utilisateur);


    }
}
