/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvilleda.services;

import com.dvilleda.dao.CompteDAO;
import com.dvilleda.model.Compte;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Danny Alexander Villeda
 */
public class CompteService {

    private CompteDAO dao;

    public void setDao(CompteDAO dao) {
        this.dao = dao;
    }

    public Compte findCompte(String username, String password){
        Compte unCompte;
        unCompte = dao.findByName(username,password);
        
        return unCompte;
    }
    public boolean creerCompte(String username, String password){
        Boolean resultat;
        Compte unCompte = new Compte();
        unCompte.setNiveau(1);
        unCompte.setUser(username);
        unCompte.setPassword(password);
        
        resultat = dao.create(unCompte);
        
        return resultat;
    }
            
    public List<Compte> getCompteListe() {

        List<Compte> liste = new LinkedList<>();
        ListIterator<Compte> iterateur = dao.findAll().listIterator();

        while (iterateur.hasNext()) {
            liste.add(iterateur.next());
        }
        return liste;
    }
}
