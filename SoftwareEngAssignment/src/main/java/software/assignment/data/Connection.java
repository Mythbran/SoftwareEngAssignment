//This class isn't the one causing the NPE. Must be another class before this one... 

package software.assignment.data;


import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Connection{

    //CONNNECTION TEST THINGS
    private static String url = "jdbc:mysql://localhost:3306/softwareeng";
    private static Connection con;
    private static String username = "root";
    private static String password = "sesame";
    private static String driverName = "com.mysql.jdbc.Driver";
    
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
    
    public static Connection getConnectionTest(){
        try{
            Class.forName(driverName);
            try{
                con = (Connection) DriverManager.getConnection(url, username, password);
            }catch(SQLException e){
                System.out.println("Failed to create database connection");
            }
        }catch(ClassNotFoundException e){
            System.out.println("Driver not found");
        }
        return con;
    }
    public void freeConnection(java.sql.Connection c){
        try{
            c.close();
        }catch(SQLException e){
            System.err.println(e);
        }
    }
}