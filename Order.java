package software.assignment.business;
/**
 *
 * @author Danton
 */
public class Order {

    // INITIALIZING ALL VARIABLES 

    private int uid;
    private int id;
    private String dateRented = ""; 
    private String dateReturned = "";
    private boolean active = true;

    public int getUid() {
        return uid;
    }

    public int getId() {
        return id;
    }

    public String getDateRented() {
        return dateRented;
    }

    public String getDateReturned() {
        return dateReturned;
    }

    public boolean isActive() {
        return active;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDateRented(String dateRented) {
        this.dateRented = dateRented;
    }

    public void setDateReturned(String dateReturned) {
        this.dateReturned = dateReturned;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}