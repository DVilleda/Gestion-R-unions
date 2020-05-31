/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvilleda.dao.Rowmappers;

import com.dvilleda.model.Contenu;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
/**
 *
 * @author Danny Alexander Villeda
 */
public class ContenuMapper implements RowMapper<Contenu>{

    @Override
    public Contenu mapRow(ResultSet rs, int i) throws SQLException {
        Contenu contenu = new Contenu();
        contenu.setId(rs.getInt("ID"));
        contenu.setContenu(rs.getString("contenu"));
        contenu.setDate(rs.getString("Date_Modifie"));
        contenu.setId_dossier(rs.getInt("Dossier_ID"));
        
        return contenu;
    }
    
}
