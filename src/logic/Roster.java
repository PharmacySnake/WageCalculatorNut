package logic;

import java.util.ArrayList;
import java.util.List;

//This class holds information on all shifts and employees attached to them.
//It can also provide specific lists 
public class Roster {

    private final List<Shift> roster;
    private final Staff staff;
    private Employee newEmployee;
    private Shift shift;

    protected Roster(Staff staff) {
        this.roster = new ArrayList<>();
        this.staff = staff;
    }

    protected void addToList(String[] line) {
        if (!this.staff.checkID(line[1])) {
            createEmployee(line[0], line[1]);
        }

        createShift(line[0], line[2], line[3], line[4]);
        this.roster.add(this.shift);
    }

    private void createEmployee(String name, String id) {
        this.newEmployee = new Employee(name, id);
        this.staff.addEmployee(this.newEmployee);
    }

    private void createShift(String name, String date, String start, String end) {
        this.shift = new Shift(this.staff.getEmployeeByName(name), date, start, end);
    }

    public List listByName(String name) {
        List<Shift> newList = new ArrayList();

        for (int i = 0; i < this.roster.size(); i++) {
            if (this.roster.get(i).getEmployeeByName().contains(name)) {
                newList.add(this.roster.get(i));
            }
        }

        return newList;
    }

    private List listByID(String id) {
        List<Shift> newList = new ArrayList();

        for (int i = 0; i < this.roster.size(); i++) {
            if (this.roster.get(i).getEmployeeByID().contains(id)) {
                newList.add(this.roster.get(i));
            }
        }

        return newList;
    }

    private List getList() {
        return this.roster;
    }

    public Staff getStaff() {
        return this.staff;
    }

    @Override
    public String toString() {
        String list = "";

        for (int i = 0; i < this.roster.size(); i++) {
            list += this.roster.get(i) + "\n";
        }
        
        return list;
    }
}
