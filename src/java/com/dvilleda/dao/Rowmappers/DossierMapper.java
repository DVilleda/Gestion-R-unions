/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvilleda.dao.Rowmappers;

import com.dvilleda.model.Dossier;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
/**
 *
 * @author Danny Alexander Villeda
 */
public class DossierMapper implements RowMapper<Dossier>{

    @Override
    public Dossier mapRow(ResultSet rs, int i) throws SQLException {
       Dossier dossier = new Dossier();
       dossier.setId(rs.getInt("ID"));
       dossier.setTitre(rs.getString("Titre"));
       dossier.setEstActif(rs.getBoolean("estActif"));
        
       return dossier;
    }
    
}
