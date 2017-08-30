package logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVreader {

    private FileReader fileReader;
    private BufferedReader buffReader;
    private String file;
    private final Roster roster;

    public CSVreader(String file, Roster roster) {
        this.file = file;
        this.roster = roster;
    }

    //This method goes through the given file and adds the received information to the class Roster
    public void readCSV() {
        try {
            this.fileReader = new FileReader(this.file);
            this.buffReader = new BufferedReader(this.fileReader);
            this.buffReader.readLine();
            String stringRead = this.buffReader.readLine();

            while (stringRead != null) {
                String[] line = stringRead.split(",");

                stringRead = this.buffReader.readLine();
                this.roster.addToList(line);
            }
            this.buffReader.close();

        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public void setFile(String newFile) {
        this.file = formatFilePath(newFile);
    }

    //This method replaces \ with / so that the path can be read.
    public String formatFilePath(String newFile) {
        newFile = newFile.replace("\\", "//");

        return newFile;
    }

}
