/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvilleda.services;

import com.dvilleda.dao.ContenuDAO;
import com.dvilleda.model.Contenu;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Danny Alexander Villeda
 */
public class ContenuService {
    private ContenuDAO dao;
    
    public void setDao(ContenuDAO dao) {
        this.dao = dao;
    }
    
    public Contenu getContenu(String x){
         Contenu contenu;
         contenu = dao.findById(x);
         
         return contenu;
     }
    
    public List<Contenu> findByIdDossier(int x){
        List<Contenu> liste = new LinkedList<>();
        ListIterator<Contenu> iterateur = dao.findByIdDossier(x).listIterator();
        
        while (iterateur.hasNext()) {
            liste.add(iterateur.next());
        }
        return liste;
    }
    public List<Contenu> getContenus() {

        List<Contenu> liste = new LinkedList<>();
        ListIterator<Contenu> iterateur = dao.findAll().listIterator();

        while (iterateur.hasNext()) {
            liste.add(iterateur.next());
        }
        return liste;
    }
    
    public boolean creerContenu(Contenu contenu){
        boolean resultat;
        resultat = dao.create(contenu);
        return resultat;
    }
    
    public boolean setContenu(Contenu x){
        boolean resultat;
        resultat = dao.update(x);
        return resultat;
    }
}
