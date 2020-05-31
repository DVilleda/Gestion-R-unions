/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvilleda.model;

/**
 *
 * @author Danny Alexander Villeda
 */
public class Dossier {
    private int id;
    private String titre;
    private boolean estActif;
    
    public String getTitre(){
        return titre;
    }
    
    public boolean getEstActif(){
        return estActif;
    }
    
    public void setTitre(String unTitre){
        this.titre=unTitre;
    }

    public void setEstActif(boolean estActif) {
        this.estActif = estActif;
    }
    
    public void changerActif(){
        if(estActif){
            estActif =false;
        }else if(!estActif){
            estActif=true;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
