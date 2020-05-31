/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvilleda.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import org.springframework.web.context.ServletContextAware;

/**
 *
 * @author Danny Alexander Villeda
 */
public class Connexion implements ServletContextAware{
    private Connection connection;
    private ServletContext servletContext;
   
    public Connection getInstance(){
        if (connection==null) {
            String pilote = servletContext.getInitParameter("pilote");
            String url = servletContext.getInitParameter("urlBd");
            String user = servletContext.getInitParameter("dbUser");
            String password = servletContext.getInitParameter("dbPassword");
            try {
                Class.forName(pilote);
                connection = DriverManager.getConnection(url,user,password);
                if (connection == null)
                    System.out.println("cnx est NULLLLLLLLLLLLLLLLLLLLLLL");
                else {
                    System.out.println("cnx est NON NULLLLLLLLLLLLLLLLLLLLLLL");
                }
            }
            catch (ClassNotFoundException | SQLException exp) {
                exp.printStackTrace();
            }
        }
        return connection;
    }
    @Override
    public void setServletContext(ServletContext sc) {
        this.servletContext = sc;
    }
}
