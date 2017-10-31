package software.assignment.business;

/*
* This clss will be the car object
* All important information regarding every possible rental car is here
* Use this class to initialize new vehicles for rental
* This class is complete 
*/

public class Car{
    
    private String location = "";
    private String availability = "";
    private float price; 
    private String model = "";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    
    
    
    
    
}