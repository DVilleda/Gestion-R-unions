/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvilleda.dao;

import com.dvilleda.dao.Rowmappers.ContenuMapper;
import com.dvilleda.model.Contenu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;

/**
 *
 * @author Danny Alexander Villeda
 */
public class ContenuDAO extends SqlDAO<Contenu> {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean create(Contenu x) {
        try {
            String query = "insert into contenu (Contenu,Date_Modifie,Dossier_ID)values(?,?,?)";
            jdbcTemplate.execute(query, new PreparedStatementCallback<Boolean>() {
                @Override
                public Boolean doInPreparedStatement(PreparedStatement ps)
                        throws SQLException, DataAccessException {

                    ps.setString(1, x.getContenu());
                    ps.setTimestamp(2, new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("EST")).getTime().getTime()));
                    ps.setInt(3, x.getId_dossier());

                    return ps.execute();
                }
            });
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public Contenu findById(String x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Contenu findById(int x) {
        String SQL = "select * from contenu where id = ?";
        Contenu contenu = jdbcTemplate.queryForObject(SQL,
                new Object[]{x}, new ContenuMapper());

        return contenu;
    }

    @Override
    public List<Contenu> findAll() {
        return jdbcTemplate.query("select * from contenu", new ResultSetExtractor<List<Contenu>>() {
            @Override
            public List<Contenu> extractData(ResultSet rs) throws SQLException,
                    DataAccessException {

                List<Contenu> list = new ArrayList<>();
                while (rs.next()) {
                    Contenu e = new Contenu();
                    e.setId(rs.getInt("ID"));
                    e.setContenu(rs.getString("Contenu"));
                    e.setDate(rs.getString("Date_Modifie"));
                    list.add(e);
                }
                return list;
            }
        });
    }
    
    public List<Contenu> findByIdDossier(int x) {
        String query = "Select * from contenu where Dossier_ID=? ORDER BY Date_Modifie DESC";
        List<Contenu> contenus = jdbcTemplate.query(query, new Object[]{x}, new ContenuMapper());
        return contenus;

    }

    @Override
    public void delete(Contenu x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Contenu x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
