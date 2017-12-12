

package software.assignment.data;

import software.assignment.business.Member;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


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
            ps.setString(1, getfName());
            ps.setString(2, getlName());
            ps.setString(3, getPNumber());
            ps.setString(4, getCCard());
            ps.setString(5, getUName());
            String adminTemp = getAdmin();
            if(adminTemp == "yes"){
                ps.setInt(6, 1);
            }
            else{
                ps.setInt(6, 0);
            }
            ps.setString(7, getPassword);
        } catch(SQLException e){
            System.err.println(e);
        } finally{
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
            
                
    }
    
    
}
