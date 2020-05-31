/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvilleda.dao;

import com.dvilleda.dao.Rowmappers.ReunionMapper;
import com.dvilleda.model.Compte_Reunion;
import com.dvilleda.model.Reunion;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Danny Alexander Villeda
 */
public class CompteReunionDAO{

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean update(List x) {
        try {           
            String SQL = "update compte_reunion set Status= ? where Compte_ID = ? AND Reunion_ID = ?";
            jdbcTemplate.update(SQL,x.get(0),x.get(1),x.get(2));
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    public Object findById(String x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List findByIdUser(String x) {
       String query = "SELECT reunion.ID,Titre,Date,Heure,Minute,Duree_min,Reunion_ouverte FROM `compte_reunion` "
               + "Join compte ON compte.ID = compte_reunion.Compte_ID JOIN reunion on reunion.ID = compte_reunion.Reunion_ID WHERE Compte_ID=?";
        List<Reunion> reunions = jdbcTemplate.query(query,new Object[]{x}, new ReunionMapper());
        return reunions;
    }

    public List findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Compte_Reunion findById(int x,int y) {
        String sql = "SELECT * FROM compte_reunion WHERE Compte_ID = ? AND Reunion_ID = ?";

    return jdbcTemplate.queryForObject(sql, new Object[]{x,y}, (rs, rowNum) ->
            new Compte_Reunion(
                    rs.getInt("Compte_ID"),
                    rs.getInt("Reunion_ID"),
                    rs.getString("Status")
            ));
    }
    
}
