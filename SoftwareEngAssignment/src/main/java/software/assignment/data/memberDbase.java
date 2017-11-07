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
    
    //NEEDS TESTING 
    //WILL DELETE ONE MEMBER 
    public static void deleteOne(int uid){    
        Connection pool = Connection.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String DELETE = "DELETE * from member where uid="+uid;
        try{
            ps = connection.prepareStatement(DELETE);
            ps.setInt(1, uid);
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
    public static void update(Member member){
        Connection pool = Connection.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String UPDATE = "update member set"
                + "fName = ?, lName = ? "
                + "pNumber = ?, cCard = ? "
                + "uName = ?, admin = ?"
                + "where id = ?";
        try{ //fName, lName, pNumber, cCard, uName, password, admin
            ps = connection.prepareStatement(UPDATE);
            ps.setString(1, member.getfName());
            ps.setString(2, member.getlName());
            ps.setString(3, member.getpNumber());
            ps.setString(4, member.getcCard());
            ps.setString(5, member.getuName()); 
            ps.setString(6, member.getAdmin());
            ps.setInt(7, member.getId());
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
    public static ArrayList<Member> selectAll(){
        ArrayList<Member> members = new ArrayList<>();
        Connection pool = Connection.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String SELECT = "select * from member";
        try{
            ps = connection.prepareStatement(SELECT);
            rs = ps.executeQuery();
            while(rs.next()){
                Member member = new Member();
                member.setId(rs.getInt("id"));
                member.setfName(rs.getString("fName"));
                member.setlName(rs.getString("lName"));
                member.setpNumber(rs.getString("pNumber"));
                member.setcCard(rs.getString("cCard"));    
                member.setuName(rs.getString("uName")); 
                member.setAdmin(rs.getString("admin"));                
                members.add(member);
            }
            
        }catch (SQLException e){
            System.err.println(e);
            return null;
        }finally{
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return members;
    }

    //NEEDS TESTING 
    //THIS WILL SELECT ONE CAR
    //MAIN USE IS WHEN EDITING CARS FOR ADMINS 
    public static Member selectOne(int uid){
        
        Connection pool = Connection.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String SELECT = "select * from member where uid = ?";
        try{
            ps = connection.prepareStatement(SELECT);
            ps.setInt(1, uid);
            rs = ps.executeQuery();
            Member member = null;
            if(rs.next()){
                member = new Member();
                member.setId(rs.getInt("id"));
                member.setfName(rs.getString("fName"));
                member.setlName(rs.getString("lName"));
                member.setpNumber(rs.getString("pNumber"));
                member.setcCard(rs.getString("cCard"));    
                member.setuName(rs.getString("uName")); 
                member.setAdmin(rs.getString("admin"));                
            }
            return member;
            
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