/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvilleda.dao;

import com.dvilleda.dao.Rowmappers.DossierMapper;
import com.dvilleda.model.Dossier;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;

/**
 *
 * @author Danny Alexander Villeda
 */
public class DossierDAO extends SqlDAO<Dossier> {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean create(Dossier x) {
        try {
            String query = "insert into dossier (Titre,estActif) values(?,?)";
            jdbcTemplate.execute(query, (PreparedStatement ps) -> {
                ps.setString(1, x.getTitre());
                ps.setBoolean(2, x.getEstActif());
                
                return ps.execute();
            });
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public void delete(Dossier x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Dossier x) {
        try {
            String query = "update dossier set Titre = ? , estActif=? where ID=?";
            jdbcTemplate.update(query, x.getTitre(),x.getEstActif(), x.getId());
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public Dossier findById(String x) {
        String query = "Select * from dossier where Titre=?";
        return jdbcTemplate.queryForObject(query, new Object[]{x}, new DossierMapper());
    }

    @Override
    public Dossier findById(int x) {
        String SQL = "select * from dossier where id = ?";
        Dossier dossier = jdbcTemplate.queryForObject(SQL,
                new Object[]{x}, new DossierMapper());

        return dossier;
    }

    @Override
    public List<Dossier> findAll() {
        return jdbcTemplate.query("select * from dossier", new ResultSetExtractor<List<Dossier>>() {
            @Override
            public List<Dossier> extractData(ResultSet rs) throws SQLException,
                    DataAccessException {

                List<Dossier> list = new ArrayList<>();
                while (rs.next()) {
                    Dossier dossier = new Dossier();
                    dossier.setTitre(rs.getString("Titre"));
                    dossier.setEstActif(rs.getBoolean("estActif"));
                    list.add(dossier);
                }
                return list;
            }
        });
    }

}
