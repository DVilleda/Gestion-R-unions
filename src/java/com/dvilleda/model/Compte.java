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
public class Compte {
    private String username;
    private String motPasse;
    private int niveau;
    private int id;
    
    public String getUser(){
        return username;
    }
    
    public String getPassword(){
        return motPasse;
    }
    
    public int getNiveau(){
        return niveau;
    }
    
    public void setUser(String unUser){
        this.username=unUser;
    }
    
    public void setPassword(String unPassword){
        this.motPasse = unPassword;
    }
    
    public void setNiveau(int unNiveau){
        this.niveau=unNiveau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
