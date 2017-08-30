package experience;

import java.io.FileNotFoundException;
import logic.Employee;
import logic.Roster;
import logic.Shift;
import logic.WageCalculator;
import java.util.List;
import javax.swing.JTextArea;
import logic.CSVreader;

public class Controller {

    private final JTextArea result;
    private final Roster roster;
    private String entry = "";
    private WageCalculator calculator;
    private CSVreader reader;

    //Controller class receives all command requests and handles them in a centralised place
    protected Controller(JTextArea result, Roster roster) {
        this.result = result;
        this.roster = roster;
        this.reader = new CSVreader("", this.roster);
    }

    //This method gathers all needed information of the searched employee and prints it to the text area.
    protected void searchResults() {
        List<Shift> lista;
        this.result.setText("");

        for (int i = 0; i < this.roster.getStaff().getStaffList().size(); i++) {
            try {
                Employee employee = this.roster.getStaff().getEmployeeByName(entry);

                if (employee.getName().toLowerCase().equals(entry.toLowerCase())) {
                    this.result.append(employee.toString());
                    this.result.append("Date:              Time:\n");

                    lista = getListByName(employee);
                    shiftsToResults(lista);
                    calculateWage(lista);

                    String pay = String.format("%.2f", this.calculator.getSalary());

                    this.result.append("\nMonth's wage: $" + pay);
                    this.result.setEditable(false);
                    return;
                }

            } catch (NullPointerException e) {
                this.result.append("Searched person can't be found in the roster.\n"
                        + "Please search with full name.");
                return;
            }
        }
    }

    private void calculateWage(List<Shift> lista) {
        this.calculator = new WageCalculator(lista);
        this.calculator.processRoster();
    }

    private void shiftsToResults(List<Shift> lista) {
        lista.forEach((shift) -> {
            this.result.append(shift.toString());
        });
    }

    private List<Shift> getListByName(Employee employee) {
        List<Shift> lista;
        lista = this.roster.listByName(employee.getName());
        return lista;
    }

    protected void setEntry(String entry) {
        this.entry = entry;
    }

    //This method handles everything file related.
    public void fileManager(String path) {
        loadFile(path);
        readFile();
        this.result.setText("Try to search for a name on the list.");
    }

    public void loadFile(String path) {
        reader.setFile(path);
    }

    public void readFile() {
        reader.readCSV();
    }
}
