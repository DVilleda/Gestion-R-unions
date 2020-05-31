/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvilleda.dao.Rowmappers;

import com.dvilleda.model.PointOrdre;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
/**
 *
 * @author Danny Alexander Villeda
 */
public class PointMapper implements RowMapper<PointOrdre>{

    @Override
    public PointOrdre mapRow(ResultSet rs, int i) throws SQLException {
        PointOrdre point = new PointOrdre();
        point.setId(rs.getInt("ID"));
        point.setNom(rs.getString("nom"));
        point.setDescription(rs.getString("description_point"));
        point.setReunion_id(rs.getInt("reunion_id"));
        
        return point;
    }
    
}
