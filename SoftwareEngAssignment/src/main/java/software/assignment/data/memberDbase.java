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
import software.assignment.business.Member;

public class memberDbase{
    
        //NEED TO TEST 
    //WILL INSERT MEMBERS INTO THE MEMBER DATABASE 
    //WILL HAVE TO IMPLEMENT HASHING FOR THE PASSWORDS EVENTUALLY 
    //DON'T WANT THEM CLEARTEXT PASSWORDS NOW DO WE???? 
    //MAYBE SAME WITH CREDIT CARD NUMBERS... 
    //WHY NOT JUST HASH IT ALL AT THIS POINT AYYYYY ;) 
    //I'M WAY TOO TIRED AND HIGH FOR THIS SHIT 
    public static void insert(Member member){
        Connection pool = Connection.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;    
        // I WONDER IF THERES A WAY TO CHANGE THIS INTO IT'S OWN SEPERATE METHOD
        
        String INSERT = "INSERT INTO member "
                + "(fName, lName, pNumber, cCard, uName, password, admin)"
                + "VALUES (?,?,?,?, ?, ?, ?)";
        
        try{
        ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, member.getfName());
        ps.setString (2, member.getlName());
        ps.setString(3, member.getpNumber());
        ps.setString(4, member.getcCard());
        ps.setString(5, member.getuName());
        ps.setString(6, member.getPassword());
        ps.setString(7, member.getAdmin());
        
        ps.executeUpdate();
        ResultSet keys = ps.getGeneratedKeys();
        
        } catch (SQLException e) {
            System.err.println(e);
            
        } finally {
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
              
    }
}