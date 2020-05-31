/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvilleda.dao;

import com.dvilleda.dao.Rowmappers.CompteMapper;
import com.dvilleda.model.Compte;
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
public class CompteDAO extends SqlDAO<Compte> {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean create(Compte x) {
        try {
            String query = "insert into compte(username,mot_passe,niveau) values(?,?,?)";
            jdbcTemplate.execute(query, new PreparedStatementCallback<Boolean>() {
                @Override
                public Boolean doInPreparedStatement(PreparedStatement ps)
                        throws SQLException, DataAccessException {

                    ps.setString(1, x.getUser());
                    ps.setString(2, x.getPassword());
                    ps.setInt(3, x.getNiveau());

                    return ps.execute();
                }
            });
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    public Compte findByName(String username, String password) {
        try {
            String query = "Select * from compte where username= ? and mot_passe= ?";
            return jdbcTemplate.queryForObject(query, new Object[]{username, password}, new CompteMapper());

        } catch (DataAccessException e) {
            Compte compte = null;
            return compte;
        }
    }

    @Override
    public List<Compte> findAll() {
        return jdbcTemplate.query("select * from compte", new ResultSetExtractor<List<Compte>>() {
            @Override
            public List<Compte> extractData(ResultSet rs) throws SQLException,
                    DataAccessException {

                List<Compte> list = new ArrayList<>();
                while (rs.next()) {
                    Compte e = new Compte();
                    e.setId(rs.getInt("ID"));
                    e.setUser(rs.getString("username"));
                    e.setPassword(rs.getString("mot_passe"));
                    e.setNiveau(rs.getInt("niveau"));
                    list.add(e);
                }
                return list;
            }
        });
    }

    @Override
    public void delete(Compte x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Compte x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Compte findById(String x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Compte findById(int x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
