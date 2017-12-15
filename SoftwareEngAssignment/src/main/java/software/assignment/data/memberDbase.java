/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.assignment.data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import software.assignment.business.Member;
import software.assignment.util.Encryption;

public class memberDbase{
    
    //TESTED AND WORKING **** :) :D:D:D:D:D:D:D:D:D:D:D:D:D:D:D:D:D:D:D:D FINALLY
    //WILL INSERT MEMBERS INTO THE MEMBER DATABASE 
    //WILL HAVE TO IMPLEMENT HASHING FOR THE PASSWORDS EVENTUALLY 
    //DON'T WANT THEM CLEARTEXT PASSWORDS NOW DO WE???? 
    //MAYBE SAME WITH CREDIT CARD NUMBERS... 
    //WHY NOT JUST HASH IT ALL AT THIS POINT AYYYYY ;) 
    public static void insert(Member member){
        Connection pool = Connection.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String INSERT = "INSERT INTO member "
                + "(fName, lName, pNumber, cCard, uName, password, admin) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
     
        try{
        ps = connection.prepareStatement(INSERT);
        ps.setString(1, member.getfName());
        ps.setString(2, member.getlName());
        ps.setString(3, member.getpNumber());
        ps.setString(4, member.getcCard());
        ps.setString(5, member.getuName());
        String passT = Encryption.encode(member.getPassword());
        ps.setString(6, passT);
        ps.setInt(7, 0);
        
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
    public static void deleteOne(int uid){    
        Connection pool = Connection.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String DELETE = "DELETE from member where uid = ?";
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
    
    
    //WIL UPDATE ONE MEMBER
    //THIS WILL NOT UPDATE PASSWORDS
    //WILL MAKE ANOTHER ONE FOR UPDATING JUST PASSWORDS BASED ON A USERNAME 
    //ONNLY USABLE VIA ADMIN
    public static void adminUpdate(Member member){
        Connection pool = Connection.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String UPDATE = "update member set "
                + "fName = ?, lName = ?, "
                + "pNumber = ?, cCard = ?, "
                + "admin = ? "
                + "where uid = ?";
        try{ //fName, lName, pNumber, cCard, uName, admin
            ps = connection.prepareStatement(UPDATE);
            ps.setString(1, member.getfName());
            ps.setString(2, member.getlName());
            ps.setString(3, member.getpNumber());
            ps.setString(4, member.getcCard()); 
            String adminT = member.getAdmin();
            int adminI;
            if (adminT.equals("yes")){
                adminI = 1; 
            }else{
                adminI = 0;
            }
            ps.setInt(5, adminI);
            ps.setInt(6, member.getUid());                       
            ps.executeUpdate();
        }catch (SQLException e){
            System.err.println(e);
        }finally{
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    //UPDATE FOR MEMBER EDIT CLASS
    //THIS WILL BE ALMOST IDENTICAL TO ADMIN 
    //DIFFERENCE IT MEMBER WONT HAVE THE ADMIN SET
    //MEMBERS CAN'T SET ADMIN PRIVELEGE
    public static void memberUpdate(Member member){
        Connection pool = Connection.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String UPDATE = "update member set "
                + "fName = ?, lName = ?, "
                + "pNumber = ?, cCard = ?, "
                + "where uid = ?";
        try{ //fName, lName, pNumber, cCard, uName
            ps = connection.prepareStatement(UPDATE);
            ps.setString(1, member.getfName());
            ps.setString(2, member.getlName());
            ps.setString(3, member.getpNumber());
            ps.setString(4, member.getcCard()); 
            ps.setInt(6, member.getUid());                       
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
                String admin= null;
                Member member = new Member();
                member.setUid(rs.getInt("uid"));
                member.setfName(rs.getString("fName"));
                member.setlName(rs.getString("lName"));
                member.setpNumber(rs.getString("pNumber"));
                member.setcCard(rs.getString("cCard"));    
                member.setuName(rs.getString("uName")); 
                int adminTemp = rs.getInt("admin");
                if (adminTemp == 1){
                    admin = "yes";
                }
                else{
                    admin = "no";
                }
                member.setAdmin(admin);                
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
            while(rs.next()){
                member = new Member();
                member.setUid(rs.getInt("uid"));
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
    
    //THIS DOES PASS RETRIEVALS 
    //LIT THINGS HERE
    //THIS IS FOR LOGINSSSSSS
    //IT WORKSSSS
    //:'D
    public static boolean passRetrieve(String uName, String password){
        Connection pool = Connection.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String userPass = null;
        boolean check = false; 
        
        String SELECT = "select password from member where uName = ?";
        try{          
            ps = connection.prepareStatement(SELECT);
            ps.setString(1, uName);
            rs = ps.executeQuery();
            while(rs.next()){
                userPass = rs.getString("password");
            }
            
            if(Encryption.match(password, userPass)){
                check = true;
            }else{
                check = false;
            }
            
            return check;        
        
        } catch(SQLException e){
            System.err.println(e);
            return false;
        }finally{ 
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    //THIS IS GOING TO CHECK FOR MEMBER
    //WILL ALSO CHECK THE ADMIN FLAG
    //USE FOR RESTRICTED WEBPAGES
    //RETURNING 2 WILL MEAN ADMIN
    //RETURNING 1 WILL MEAN MEMBER
    //RETURNING 0 WILL MEAN NO ACCOUNT EXISTS 
    public static int role(String uName){
        Connection pool = Connection.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int admin = 0;
        int role = 0;
        String SELECT = "select admin from member where uName = ?";
        
        try{
            ps = connection.prepareStatement(SELECT);
            ps.setString(1, uName);
            rs = ps.executeQuery();
            if(rs != null){
                while(rs.next()){
                    admin = rs.getInt("admin");
                }
                switch(admin){
                    case 1:{
                        role = 2;
                        break;    
                    }
                    case 0:{
                        role = 1;
                        break; 
                    }
                    default:{
                        role = 0;
                    }
                }
            }
            else{
                return 0;
            }
            }catch(SQLException e){
                System.err.println(e);
                return 0;
        } 
        return role;
    }
    
}