package software.assignment.data;


import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Connection{
    
    private static Connection pool = null;
    private static DataSource dataSource = null;
    
    private Connection(){
        try{
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/softwareeng");
        }catch (NamingException e){
            System.err.println(e);
        }
    }
    
    public static synchronized Connection getInstance(){
        if (pool == null){
            pool = new Connection();
        }
        return pool;
    }
    
    public java.sql.Connection getConnection(){
        try{
            return dataSource.getConnection();
        }catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }
    
    public void freeConnection(java.sql.Connection c){
        try{
            c.close();
        }catch(SQLException e){
            System.err.println(e);
        }
    }
}