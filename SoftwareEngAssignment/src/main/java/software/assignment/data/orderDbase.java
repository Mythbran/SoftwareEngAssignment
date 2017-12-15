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
import java.util.ArrayList;
import software.assignment.business.Order;



/**
 *
 * @author Danton
 */
public class orderDbase {
        //NEED TO TEST 
    //WILL INSERT MEMBERS INTO THE MEMBER DATABASE 
    //WILL HAVE TO IMPLEMENT HASHING FOR THE PASSWORDS EVENTUALLY 
    //DON'T WANT THEM CLEARTEXT PASSWORDS NOW DO WE???? 
    //MAYBE SAME WITH CREDIT CARD NUMBERS... 
    //WHY NOT JUST HASH IT ALL AT THIS POINT AYYYYY ;) 
    //I'M WAY TOO TIRED AND HIGH FOR THIS SHIT 
    public static void insert(Order order){
        Connection pool = Connection.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;    
        // I WONDER IF THERES A WAY TO CHANGE THIS INTO IT'S OWN SEPERATE METHOD
        int active;

        String INSERT = "INSERT INTO orders "
                + "(uid, id, dateRented, active)"
                + "VALUES (?, ?, ?, ?";
        
        
        
        try{
        active = 0;
        if (order.isActive() == true) {
            active = 1;
        }
        
        ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, order.getUid());
        ps.setInt (2, order.getId());
        ps.setString(3, order.getDateRented());
        ps.setInt(4, active);
        
        ps.executeUpdate();
        ResultSet keys = ps.getGeneratedKeys();
        
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
        String DELETE = "DELETE * from order where oid = ?";
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
                + "uid = ?, id = ? "
                + "dateRented = ?, dateReturned = ? "
                + "active = ?";
        try{ //fName, lName, pNumber, cCard, uName, password, admin
        int active = 0;
            if (order.isActive() == true) {
            active = 1;
        }
            ps = connection.prepareStatement(UPDATE);
            ps.setInt(1, order.getUid());
            ps.setInt(2, order.getId());
            ps.setString(3, order.getDateRented());
            ps.setString(4, order.getDateReturned());
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
        String SELECT = "select * from order";
        try{
            ps = connection.prepareStatement(SELECT);
            rs = ps.executeQuery();
            boolean active;
            while(rs.next()){
                Order order = new Order();
                order.setOid(rs.getInt("oid"));
                order.setUid(rs.getInt("uid"));
                order.setId(rs.getInt("id"));
                order.setDateRented(rs.getString("dateRented"));
                order.setDateReturned(rs.getString("dateReturned"));
                int activeTemp = rs.getInt("active");
                if (activeTemp == 1){
                    active = true;
                }
                else{
                    active = false;
                }
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
        
        String SELECT = "select * from order where oid = ?";
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
                order.setDateRented(rs.getString("dateRented"));
                order.setDateReturned(rs.getString("dateReturned"));
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
