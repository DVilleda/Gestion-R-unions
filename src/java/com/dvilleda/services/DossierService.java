/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvilleda.services;

import com.dvilleda.dao.DossierDAO;
import com.dvilleda.model.Dossier;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Danny Alexander Villeda
 */
public class DossierService {
    
    private DossierDAO dao;
    
    public void setDao(DossierDAO dao){
        this.dao = dao;
    }
    
    public Dossier getDossier(String x){
         Dossier dossier;
         dossier = dao.findById(x);
         return dossier;
     }
    
    public Dossier getDossierID(int x){
         Dossier dossier;
         dossier = dao.findById(x);
         return dossier;
     }
    
    public List<Dossier> getDossiers() {

        List<Dossier> liste = new LinkedList<>();
        ListIterator<Dossier> iterateur = dao.findAll().listIterator();

        while (iterateur.hasNext()) {
            liste.add(iterateur.next());
        }
        return liste;
    }
    
    public boolean creerDossier(Dossier dossier){
        boolean resultat;
        resultat = dao.create(dossier);
        return resultat;
    }
    
    public boolean setDossier(Dossier x){
        boolean resultat;
        resultat = dao.update(x);
        return resultat;
    }
    
}
