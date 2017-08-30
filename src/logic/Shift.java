package logic;

public class Shift {

    private final Employee employee;
    private final String shiftDate;
    private final String start;
    private final String end;

    //Everything one needs to know of a shift is located here.
    protected Shift(Employee employee, String shiftDate, String start, String end) {
        this.employee = employee;
        this.shiftDate = shiftDate;
        this.start = start;
        this.end = end;
    }

    protected String getEmployeeByName() {
        return this.employee.getName();
    }

    protected String getEmployeeByID() {
        return this.employee.getID();
    }

    protected String getStart() {
        return this.start;
    }

    protected String getEnd() {
        return this.end;
    }

    private String getDate() {
        return this.shiftDate;
    }

    private String formatPrint() {
        if (getDate().length() == 8) {
            return "0" + getDate() + "      " + getStart() + " - " + getEnd() + "\n";
        }
        
        return getDate() + "      " + getStart() + " - " + getEnd() + "\n";
    }

    @Override
    public String toString() {
        return formatPrint();
    }
}
