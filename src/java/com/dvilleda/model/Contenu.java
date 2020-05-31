/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvilleda.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Danny Alexander Villeda
 * timestamp.toInstant().atZone(zoneId).toLocalDate()
 */
public class Contenu {
    private int id;
    private String contenu;
    private String date;
    private int id_dossier;

    public int getId_dossier() {
        return id_dossier;
    }

    public void setId_dossier(int id_dossier) {
        this.id_dossier = id_dossier;
    }

    public int getId() {
        return id;
    }
    
    public String getContenu(){
        return contenu;
    }
    
    public String getDate(){
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setContenu(String unContenu){
        this.contenu= unContenu;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public String getFormattedDate(){
        String msg="";
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime datetime = LocalDateTime.parse(date,formatter);
        
        msg+= "Ce contenu a etait ecrit le : ";
        msg+=datetime.format(DateTimeFormatter.ofPattern("dd MMMM, yyyy hh:mm a"));
        return msg;
    }
    
    
    /**
     * Utiliser ca pour la creation de date lors de loperation SQL
     * Date date = new Date();
        System.out.println(new Timestamp(date.getTime()));
     */
}
