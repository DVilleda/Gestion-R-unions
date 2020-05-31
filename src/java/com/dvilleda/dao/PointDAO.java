/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvilleda.dao;

import com.dvilleda.dao.Rowmappers.PointMapper;
import com.dvilleda.model.PointOrdre;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
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
public class PointDAO extends SqlDAO<PointOrdre> {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean create(PointOrdre x) {
        try {
            String query = "insert into pointordre (nom,description_point,reunion_id)values(?,?,?)";
            jdbcTemplate.execute(query, (PreparedStatement ps) -> {
                ps.setString(1, x.getNom());
                ps.setString(2, x.getDescription());
                ps.setInt(3, x.getReunion_id());

                return ps.execute();
            });
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public void delete(PointOrdre x) {
        String query = "delete from pointordre where ID=?";
        jdbcTemplate.update(query, x.getId());
    }

    @Override
    public boolean update(PointOrdre x) {
        try {
            String query = "update pointordre set description_point=? where nom=?";
            jdbcTemplate.update(query, x.getDescription(), x.getNom());
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public PointOrdre findById(String x) {
        try {
            String query = "Select * from pointordre where nom=?";
            return jdbcTemplate.queryForObject(query, new Object[]{x}, new PointMapper());
        } catch (DataAccessException e) {
            return null;
        }
    }

    public List<PointOrdre> findByIdReunion(int x) {
        String query = "Select * from pointordre where reunion_id=?";
        List<PointOrdre> points = jdbcTemplate.query(query, new Object[]{x}, new PointMapper());
        return points;

    }

    @Override
    public List<PointOrdre> findAll() {
        return jdbcTemplate.query("select * from pointordre", new ResultSetExtractor<List<PointOrdre>>() {
            @Override
            public List<PointOrdre> extractData(ResultSet rs) throws SQLException,
                    DataAccessException {

                List<PointOrdre> list = new ArrayList<PointOrdre>();
                while (rs.next()) {
                    PointOrdre point = new PointOrdre();
                    point.setId(rs.getInt("ID"));
                    point.setNom(rs.getString("nom"));
                    point.setDescription("description_point");
                    point.setReunion_id(rs.getInt("reunion_id"));
                    list.add(point);
                }
                return list;
            }
        });
    }

    @Override
    public PointOrdre findById(int x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
