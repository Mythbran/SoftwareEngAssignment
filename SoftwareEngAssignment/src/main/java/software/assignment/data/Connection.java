/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package software.assignment.data;

import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Oracle
 */
public class Connection {

    private static Connection pool = null;
    private static DataSource dataSource = null;

    private Connection() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("**** EDIT ME ****** ");
        } catch (NamingException e) {
            System.err.println(e);
        }
    }

    public static synchronized Connection getInstance() {
        if (pool == null) {
            pool = new Connection();
        }
        return pool;
    }

    public java.sql.Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    public void freeConnection(java.sql.Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }    
}