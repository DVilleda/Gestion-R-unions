/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvilleda.services;

import com.dvilleda.dao.CompteReunionDAO;
import com.dvilleda.dao.ReunionDAO;
import com.dvilleda.model.Compte_Reunion;
import com.dvilleda.model.Reunion;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Danny Alexander Villeda
 */
public class ReunionService {

    private ReunionDAO dao;
    private CompteReunionDAO compte_reunion_dao;

    public void setDao(ReunionDAO dao) {
        this.dao = dao;
    }

    public void setCompte_reunion_dao(CompteReunionDAO compte_reunion_dao) {
        this.compte_reunion_dao = compte_reunion_dao;
    }

    public Reunion getReunion(String x) {
        Reunion reunion;
        reunion = dao.findById(x);

        return reunion;
    }
    
    public Reunion getReunionID(int x) {
        Reunion reunion;
        reunion = dao.findById(x);

        return reunion;
    }

    public List<Reunion> getReunions() {

        List<Reunion> liste = new LinkedList<>();
        ListIterator<Reunion> iterateur = dao.findAll().listIterator();

        while (iterateur.hasNext()) {
            liste.add(iterateur.next());
        }
        return liste;
    }
    
    public List<Reunion> getReunionsCompte(String x){
        List<Reunion> liste = new LinkedList<>();
        ListIterator<Reunion> iterateur = compte_reunion_dao.findByIdUser(x).listIterator();

        while (iterateur.hasNext()) {
            liste.add(iterateur.next());
        }
        return liste;
    }
    
    public List<Compte_Reunion> getStatusReunion(String x){
        List<Compte_Reunion> liste = new ArrayList<>();
        List<Reunion> listeCompte = getReunionsCompte(x);
        
        for(int i=0;i<listeCompte.size();i++){
            liste.add(compte_reunion_dao.findById(Integer.parseInt(x),listeCompte.get(i).getId()));
        }
        
        return liste;
    }
    
    public void changerStatus(List x){
        compte_reunion_dao.update(x);
    }

    public boolean creerReunion(Reunion reunion) {
        boolean resultat;
        resultat = dao.create(reunion);
        return resultat;
    }

    public boolean setReunion(Reunion x) {
        boolean resultat;
        resultat = dao.update(x);
        return resultat;
    }
    
    public void deleteReunion(Reunion x){
        dao.delete(x);
    }
}
