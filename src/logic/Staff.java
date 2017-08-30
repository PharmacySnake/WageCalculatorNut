package logic;

import java.util.ArrayList;
import java.util.List;

//All of the employees are gathered here in to a staff list.
public class Staff {

    protected List<Employee> staff = new ArrayList<>();

    protected void addEmployee(Employee employee) {
        this.staff.add(employee);
    }

    public List getStaffList() {
        return this.staff;
    }

    protected boolean checkID(String string) {
        for (int i = 0; i < this.staff.size(); i++) {
            if (this.staff.get(i).getID().equals(string)) {
                return true;
            }
        }

        return false;
    }

    public Employee getEmployeeByName(String name) {
        Employee employee = null;

        for (int i = 0; i < this.staff.size(); i++) {
            if (this.staff.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
                employee = this.staff.get(i);
            }
        }

        return employee;
    }

    @Override
    public String toString() {
        String list = "";

        for (int i = 0; i < this.staff.size(); i++) {
            list += this.staff.get(i);
        }

        return list;
    }
}
