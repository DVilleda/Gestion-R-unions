/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvilleda.dao.Rowmappers;

import com.dvilleda.model.Reunion;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
/**
 *
 * @author Danny Alexander Villeda
 */
public class ReunionMapper implements RowMapper<Reunion>{

    @Override
    public Reunion mapRow(ResultSet rs, int i) throws SQLException {
        Reunion reunion = new Reunion();
        reunion.setId(rs.getInt("ID"));
        reunion.setTitre(rs.getString("Titre"));
        reunion.setDate(rs.getDate("Date").toLocalDate().atTime(rs.getInt("Heure"), rs.getInt("Minute")));
        reunion.setReunion_ouverte(rs.getBoolean("Reunion_ouverte"));
        reunion.setDuree(rs.getInt("Duree_min"));
        
        
        return reunion;
    }
    
}
