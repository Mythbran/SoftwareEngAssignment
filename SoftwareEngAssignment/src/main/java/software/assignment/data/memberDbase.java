

package software.assignment.data;

import software.assignment.business.Member;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class memberDbase{
    
    public static void insert(Member member){
        Connection pool = Connection.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String INSERT = "INSERT INTO member"
                + "(fName, lName, pNumber, cCard, uName, admin, password)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?";
        try{
            ps = connection.prepareStatement(INSERT);
            ps.setString(1, member.getfName());
            ps.setString(2, member.getlName());
            ps.setString(3, member.getpNumber());
            ps.setString(4, member.getcCard());
            ps.setString(5, member.getuName());
            String adminTemp = member.getAdmin();
            if(adminTemp == "yes"){
                ps.setInt(6, 1);
            }
            else{
                ps.setInt(6, 0);
            }
            ps.setString(7, member.getPassword());
        } catch(SQLException e){
            System.err.println(e);
        } finally{
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
            
                
    }
    
    
}
