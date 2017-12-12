/*
* This class holds all information about a members 
* This class in complete 
*/


package software.assignment.business;

import javax.validation.constraints.*;

public class Member{
    
    //INSTANTIATING ALL VARIABLES 
    private int uid = 0; 
    
    private String admin = "no";
        
    @NotNull (message = "Missing First Name")
    @Pattern(regexp = "([a-zA-Z'.,-]+\\s*)+", message = "Invalid first name")
    private String fName = "";
    
    @NotNull (message = "Missing Last Name")
    @Pattern(regexp = "([a-zA-Z'.,-]+\\s*)+", message = "Invalid last name")
    private String lName = "";  
    
    @NotNull (message = "Missing Phone Number")
    @Pattern(regexp = "1-9", message = "Invalid Phone Number")
    private String pNumber = "";    
    
    @NotNull (message = "Missing Credit Card Number")
    @Pattern(regexp = "1-9", message = "Invalid Credit Card Number")
    private String cCard = "";       

    @NotNull (message = "Missing UserName")
    @Pattern(regexp = "([a-zA-Z'.,-]+\\s*)+", message = "Invalid username")
    private String uName = "";
    
    @NotNull (message = "Missing password")
    @Pattern(regexp = "([a-zA-Z'.,-]+\\s*)+", message = "Invalid password")
    private String password = "";  
    
    //GETTERS AND SETTERS FOR ALL VARIABLES 
    
    //ADMIN
    //HAVE BOTH A STRING AND A BOOLEAN 
    //FIGURE OUT WHICH ONE WORKS
    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
  

    
    //ACCOUNT ID 
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
    
    //FIRST NAME 
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    //LAST NAME
    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    //PHONE NUMBER
    public String getpNumber() {
        return pNumber;
    }

    public void setpNumber(String pNumber) {
        this.pNumber = pNumber;
    }

    //CREDIT CARD
    public String getcCard() {
        return cCard;
    }

    public void setcCard(String cCard) {
        this.cCard = cCard;
    }
    
    //USERNAME & PASSWORD
    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}