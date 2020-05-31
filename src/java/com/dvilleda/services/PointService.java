/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvilleda.services;

import com.dvilleda.dao.PointDAO;
import com.dvilleda.model.PointOrdre;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Danny Alexander Villeda
 */
public class PointService {
    
    private PointDAO dao;
    
    public void setDao(PointDAO dao){
        this.dao = dao;
    }
    
    public boolean creerpoint(PointOrdre point){
        boolean resultat;
        resultat = dao.create(point);
        return resultat;
    }
    
    public List<PointOrdre> findByIdReunion(int x){
        List<PointOrdre> liste = new LinkedList<>();
        ListIterator<PointOrdre> iterateur = dao.findByIdReunion(x).listIterator();
        
        while (iterateur.hasNext()) {
            liste.add(iterateur.next());
        }
        return liste;
    }
    
    public PointOrdre getPoint(String x){
        PointOrdre point;
        point =dao.findById(x);
        return point;
    }
    
    public boolean setContenu(PointOrdre x){
        boolean resultat;
        resultat = dao.update(x);
        return resultat;
    }
    
    public void deletePoint(PointOrdre x){
        dao.delete(x);
    }
}
