/*
* This class holds all information about a members 
* This class in complete 
*/


package software.assignment.business;

import javax.validation.constraints.*;

public class Member{
    
    //INSTANTIATING ALL VARIABLES 
    private int id = 0; 
    
    private boolean administrator = false;
    
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

    //GETTERS AND SETTERS FOR ALL VARIABLES 
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getpNumber() {
        return pNumber;
    }

    public void setpNumber(String pNumber) {
        this.pNumber = pNumber;
    }

    public String getcCard() {
        return cCard;
    }

    public void setcCard(String cCard) {
        this.cCard = cCard;
    }
    
    
}