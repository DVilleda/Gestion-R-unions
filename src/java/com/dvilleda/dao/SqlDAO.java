/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvilleda.dao;

import java.util.List;
import com.dvilleda.jdbc.Connexion;

/**
 *
 * @author Danny Alexander Villeda
 */
public abstract class SqlDAO<T> {
    protected Connexion connexion;

    public void setConnexion(Connexion connexion) {
        this.connexion = connexion;
    }    
    public abstract boolean create(T x);
    public abstract void delete(T x);
    public abstract boolean update(T x);
    public abstract T findById(String x);
    public abstract T findById(int x);
    public abstract List<T> findAll();
}
