package software.assignment.business;

import java.sql.Date;

/**
 *
 * @author Danton
 */
public class Order {

    // INITIALIZING ALL VARIABLES 
    private int oid;
    private int uid;
    private int id;
    private java.sql.Date dateRented; 
    private java.sql.Date dateReturned;
    private boolean active = true;

    public int getOid() {
        return oid;
    }
    
    public int getUid() {
        return uid;
    }

    public int getId() {
        return id;
    }

    public Date getDateRented() {
        return dateRented;
    }

    public Date getDateReturned() {
        return dateReturned;
    }

    public void setDateRented(Date dateRented) {
        this.dateRented = dateRented;
    }

    public void setDateReturned(Date dateReturned) {
        this.dateReturned = dateReturned;
    }

    public boolean isActive() {
        return active;
    }

    public void setOid(int uid) {
        this.uid = oid;
    }
    
    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setId(int id) {
        this.id = id;
    }



    public void setActive(boolean active) {
        this.active = active;
    }

}
