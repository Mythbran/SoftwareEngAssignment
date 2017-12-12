

package software.assignment.data;

import java.sql.DriverManager;
import software.assignment.business.Member;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;



public class memberDbase{
    
    public static void insert(Member member){
       
        try{
        //Class.forName("com.mysql.jbdc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareeng?zeroDateTimeBehavior=convertToNull", "root", "sesame");
        
        Statement st = con.createStatement();        
        String INSERT = "INSERT INTO test"
                + "(test)"
                + "VALUES ()";
        
            st.executeUpdate(INSERT); 
            System.out.println("fuck this shit");
        }catch(SQLException e){
            System.out.println("data not insertted sucalsikdfghjn");
        } 
            
                
    }
    
    
}
