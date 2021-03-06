/*
* THIS CLASS WILL HANDLE ALL DATABASE COMMANDS TO CAR 
* USE THIS TO EDIT ANYTHING IN RELATION TO CAR *
*/

package software.assignment.data;

import software.assignment.business.Car;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class carDbase{
 
    //WILL INSERT CARS INTO THE CAR DATABASE 
    public static void insert(Car car){
        Connection pool = Connection.getInstance();
        java.sql.Connection connection = pool.getConnection();

        
        PreparedStatement ps = null;    
        
        String INSERT = "INSERT INTO car "
                + "(model, make, price, availability, location) "
                + "VALUES (?,?,?,?,?)";
        
        try{
        ps = connection.prepareStatement(INSERT);
        ps.setString(1, car.getModel());
        ps.setString(2, car.getMake());
        ps.setFloat(3, car.getPrice());
        ps.setString(4, car.getAvailability());
        ps.setString(5, car.getLocation());
        ps.executeUpdate();
        
        } catch (SQLException e) {
            System.err.println(e);
            
        } finally {
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
             
    }
    
    //WILL DELETE ONE CAR 
    public static void deleteOne(int id){    
        Connection pool = Connection.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String DELETE = "DELETE from car where id = ?";
        try{
            ps = connection.prepareStatement(DELETE);
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (SQLException e){
            System.err.println(e);
            
        }finally{
            
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
               
    }
   
    //WIL UPDATE ONE CAR
    public static void update(Car car){
        Connection pool = Connection.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String UPDATE = "update car set "
                + "make = ?, model = ?, price = ?, "
                + "availability = ?, location = ? "
                + "where id = ?";
        try{
            ps = connection.prepareStatement(UPDATE);
            ps.setString(1, car.getMake());
            ps.setString(2, car.getModel());   
            ps.setFloat(3, car.getPrice());
            ps.setString(4, car.getAvailability());
            ps.setString(5, car.getLocation());
            ps.setInt(6, car.getId());
            ps.executeUpdate();
        }catch (SQLException e){
            System.err.println(e);
        }finally{
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
     
    //THIS WILL SELECT ALL CARS IN THE CAR DATABASE 
    //THIS WILL HAVE 2 PURPOSES 
    //1: USE FOR THE RENTAL PAGE 
    //2: USE FOR ADMIN EDITCAR PAGE 
    public static ArrayList<Car> selectAll(){
        ArrayList<Car> cars = new ArrayList<>();
        Connection pool = Connection.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String SELECT = "select * from car";
        try{
            ps = connection.prepareStatement(SELECT);
            rs = ps.executeQuery();
            while(rs.next()){
                Car car = new Car();
                car.setId(rs.getInt("id"));
                car.setModel(rs.getString("model"));
                car.setMake(rs.getString("make"));
                car.setPrice(rs.getFloat("price"));
                car.setAvailability(rs.getString("availability"));
                car.setLocation(rs.getString("location"));
                cars.add(car);
            }
            
        }catch (SQLException e){
            System.err.println(e);
            return null;
        }finally{
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return cars;
    }
    
    //THIS WILL SELECT ONE CAR
    //MAIN USE IS WHEN EDITING CARS FOR ADMINS 
    public static Car selectOne(int uid){
        
        Connection pool = Connection.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String SELECT = "select * from car where id = ?";
        try{
            ps = connection.prepareStatement(SELECT);
            ps.setInt(1, uid);
            rs = ps.executeQuery();
            Car car = null;
            if(rs.next()){
                car = new Car();
                car.setId(uid);
                car.setModel(rs.getString("model"));
                car.setMake(rs.getString("make"));
                car.setPrice(rs.getFloat("price"));
                car.setAvailability(rs.getString("availability"));
                car.setLocation(rs.getString("location"));
            }
            return car;
            
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