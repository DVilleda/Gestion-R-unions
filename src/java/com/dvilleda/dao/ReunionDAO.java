/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvilleda.dao;

import com.dvilleda.dao.Rowmappers.ReunionMapper;
import com.dvilleda.model.Reunion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

/**
 *
 * @author Danny Alexander Villeda
 */
public class ReunionDAO extends SqlDAO<Reunion> {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean create(Reunion x) {
        try {
            String query = "insert into reunion(Titre,Date,Heure,Minute,Duree_min,Reunion_ouverte) values(?,?,?,?,?,?)";
            jdbcTemplate.execute(query, new PreparedStatementCallback<Boolean>() {
                @Override
                public Boolean doInPreparedStatement(PreparedStatement ps)
                        throws SQLException, DataAccessException {

                    ps.setString(1, x.getTitre());
                    ps.setDate(2, Date.valueOf(x.getDate().toLocalDate()));
                    ps.setInt(3, x.getDate().getHour());
                    ps.setInt(4, x.getDate().getMinute());
                    ps.setInt(5, x.getDuree());
                    ps.setBoolean(6, x.isReunionOuverte());

                    return ps.execute();
                }
            });
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public void delete(Reunion x) {
        String query = "delete from reunion where ID=?";
        jdbcTemplate.update(query, x.getId());
    }

    @Override
    public boolean update(Reunion x) {
        try {
            String SQL = "update reunion set Date=?, Heure=?, Minute=?, Duree_min=?, Reunion_ouverte = ? where titre = ?";
            jdbcTemplate.update(SQL, x.getDate().toLocalDate(), x.getDate().getHour(), x.getDate().getMinute(),
                    x.getDuree(), x.isReunionOuverte(), x.getTitre());
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public Reunion findById(String x) {
        try {
            String query = "Select * from reunion where titre=?";
            return jdbcTemplate.queryForObject(query, new Object[]{x}, new ReunionMapper());
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public Reunion findById(int x) {
        try {
            String query = "Select * from reunion where ID=?";
            return jdbcTemplate.queryForObject(query, new Object[]{x}, new ReunionMapper());
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Reunion> findAll() {
        String query = "Select * from reunion";
        List<Reunion> reunions = jdbcTemplate.query(query, new ReunionMapper());
        return reunions;
    }
}
