/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvilleda.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Danny Alexander Villeda
 */
public class PointOrdre {
    private int id;
    private String nom;
    private String description;
    private LocalDate date;
    private int reunion_id;
    
    public String getNom(){
        return nom;
    }
    
    public String getDescription(){
        return description;
    }
    
    public LocalDate getDate(){
        return date;
    }
    
    public void setNom(String unNom){
        this.nom=unNom;
    }
    
    public void setDescription(String uneDescription){
        this.description=uneDescription;
    }
    
    public void setDate(LocalDate uneDate){
        this.date=uneDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReunion_id() {
        return reunion_id;
    }

    public void setReunion_id(int reunion_id) {
        this.reunion_id = reunion_id;
    }
    
    public String getFormattedDate(){
        String msg="";
        
        msg+= "Ce contenu a etait ecrit le : ";
        msg+=date.format(DateTimeFormatter.ofPattern("dd MMMM, yyyy"));
        return msg;
    }
    
    
}
