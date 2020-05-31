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
 */
public class Reunion {

    private int id;
    private String titre;
    private LocalDateTime date;
    private int duree;
    private String status;
    private boolean reunion_ouverte;

    public String getTitre() {
        return titre;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getDuree() {
        return duree;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isReunionOuverte() {
        return reunion_ouverte;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setReunion_ouverte(boolean reunion_ouverte) {
        this.reunion_ouverte = reunion_ouverte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFormattedDate() {
        String msg = "";

        msg += "Cette reunion a etait cree le : ";
        msg += date.format(DateTimeFormatter.ofPattern("dd MMMM, yyyy hh:mm a"));
        return msg;
    }

    public String getFormattedBoolean() {
        String msg = "";
        if (reunion_ouverte) {
            msg += "Cette reunion est encore ouverte!";
        } else if (!reunion_ouverte) {
            msg += "Cette reunion a etait ferme... ";
        }
        return msg;
    }
}
