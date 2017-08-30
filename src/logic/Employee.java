package logic;

public class Employee {

    private String name;
    private String id;
    private final String address;
    private final String phone;

    //Employee class holds everyhing that needs to be known of an individual.
    public Employee(String name, String id) {
        this.name = name;
        this.id = id;
        this.address = "Jobwork Road " + (Math.round(1 + Math.random() * 9)) + ", " + (Math.round(1000 + Math.random() * 9000)) + " Worktown, Careerica";
        this.phone = "080-" + (Math.round(1000000 + Math.random() * 9000000));
    }

    public String getName() {
        return this.name;
    }

    protected String getID() {
        return this.id;
    }

    private void setName(String newName) {
        this.name = newName;
    }

    private void setID(String newID) {
        this.id = newID;
    }

    private String getAddress() {
        return this.address;
    }

    private String getPhone() {
        return this.phone;
    }

    @Override
    public String toString() {
        return "Name: " + getName()
                + "\nID number: " + getID()
                + "\nAddress: " + getAddress()
                + "\nPhone: " + getPhone()
                + "\n\n";
    }
}
