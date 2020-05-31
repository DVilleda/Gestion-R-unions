/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvilleda.dao.Rowmappers;

import com.dvilleda.model.Compte;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
/**
 *
 * @author Danny Alexander Villeda
 */
public class CompteMapper implements RowMapper<Compte>{

    @Override
    public Compte mapRow(ResultSet rs, int i) throws SQLException {
        Compte compte = new Compte();
        compte.setUser(rs.getString("username"));
        compte.setPassword(rs.getString("mot_passe"));
        compte.setNiveau(rs.getInt("niveau"));
        compte.setId(rs.getInt("ID"));
        
        return compte;
    }
    
}
