/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.assignment.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import software.assignment.business.Order;
import java.sql.Date;

import java.time.LocalDate;



/**
 *
 * @author Danton
 */
public class orderDbase {
      
    public static void insert(Order order){
        Connection pool = Connection.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;    
        // I WONDER IF THERES A WAY TO CHANGE THIS INTO IT'S OWN SEPERATE METHOD
        int active;

        Date date = Date.valueOf(LocalDate.now());
        String INSERT = "INSERT INTO orders "
                + "(uid, id, dateRented, active) "
                + "VALUES (?, ?, ?, ?)";
        
        
        
        
        try{
        active = 0;
        
        ps = connection.prepareStatement(INSERT);
        ps.setInt(1, order.getUid());
        ps.setInt (2, order.getId());
        ps.setDate(3, date);
        ps.setInt(4, active);
        
        ps.executeUpdate();
        
        } catch (SQLException e) {
            System.err.println(e);
            
        } finally {
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
              
    }
    
    //NEEDS TESTING 
    //WILL DELETE ONE MEMBER 
    public static void deleteOne(int oid){    
        Connection pool = Connection.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String DELETE = "DELETE from orders where oid = ?";
        try{
            ps = connection.prepareStatement(DELETE);
            ps.setInt(1, oid);
            ps.executeUpdate();
        }catch (SQLException e){
            System.err.println(e);
            
        }finally{
            
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
               
    }   
    
    
    //NEEDS TESTING
    //WIL UPDATE ONE MEMBER
    //THIS WILL NOT UPDATE PASSWORDS
    //WILL MAKE ANOTHER ONE FOR UPDATING JUST PASSWORDS BASED ON A USERNAME 
    public static void update(Order order){
        Connection pool = Connection.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String UPDATE = "update order set"
                + "uid = ?, id = ? ,"
                + "dateRented = ?, dateReturned = ? ,"
                + "active = ?";
        try{ //fName, lName, pNumber, cCard, uName, password, admin
        int active = 0;
            if (order.isActive() == true) {
            active = 1;
        }
            ps = connection.prepareStatement(UPDATE);
            ps.setInt(1, order.getUid());
            ps.setInt(2, order.getId());
            ps.setDate(3, order.getDateRented());
            ps.setDate(4, order.getDateReturned());
            ps.setInt(5, active); 
            ps.executeUpdate();
        }catch (SQLException e){
            System.err.println(e);
        }finally{
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    //NEEDS TESTING 
    //THIS WILL SELECT ALL MEMBERS IN THE MEMBER DATABASE 
    //THIS WILL HAVE 2 PURPOSES 
    //1: USE FOR THE ADMIN VIEW ALL MEMBERS
    public static ArrayList<Order> selectAll(){
        ArrayList<Order> orders = new ArrayList<>();
        Connection pool = Connection.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String SELECT = "select * from orders";
        
        try{
            ps = connection.prepareStatement(SELECT);
            rs = ps.executeQuery();
            while(rs.next()){
                Boolean active = false;
                Order order = new Order();
                order.setOid(rs.getInt("oid"));
                order.setUid(rs.getInt("uid"));
                order.setDateRented(rs.getDate("dateRented"));
                order.setDateReturned(rs.getDate("dateReturned"));
                active = (rs.getBoolean("active"));    
                order.setId(rs.getInt("id")); 
                order.setActive(active);                
                orders.add(order);
            }
            
        }catch (SQLException e){
            System.err.println(e);
            return null;
        }finally{
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return orders;
    }

    //NEEDS TESTING 
    //THIS WILL SELECT ONE CAR
    //MAIN USE IS WHEN EDITING CARS FOR ADMINS 
    public static Order selectOne(int oid){
        
        Connection pool = Connection.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String SELECT = "select from order where oid = ?";
        try{
            ps = connection.prepareStatement(SELECT);
            ps.setInt(1, oid);
            boolean active;
            rs = ps.executeQuery();
            Order order = null;
            if(rs.next()){
                order = new Order();
                order.setOid(rs.getInt("oid"));
                order.setUid(rs.getInt("uid"));
                order.setId(rs.getInt("id"));
                order.setDateRented(rs.getDate("dateRented"));
                order.setDateReturned(rs.getDate("dateReturned"));
                int activeTemp = rs.getInt("active");
                if (activeTemp == 1){
                    active = true;
                }
                else{
                    active = false;
                }
                order.setActive(active);              
            }
            return order;
            
        }catch (SQLException e){
            System.err.println(e);
            return null;
        }finally{
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }     
}
