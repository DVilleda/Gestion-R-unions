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
public class Compte_Reunion {
    private int compte_id;
    private int reunion_id;
    private String status="";

    public Compte_Reunion(int compte_id, int reunion_id,String status) {
        this.compte_id = compte_id;
        this.reunion_id = reunion_id;
        this.status= status;
    }

    public int getCompte_id() {
        return compte_id;
    }

    public void setCompte_id(int compte_id) {
        this.compte_id = compte_id;
    }

    public int getReunion_id() {
        return reunion_id;
    }

    public void setReunion_id(int reunion_id) {
        this.reunion_id = reunion_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
