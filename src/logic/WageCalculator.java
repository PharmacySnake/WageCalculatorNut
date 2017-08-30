package logic;

import java.util.List;

public class WageCalculator {

    private List<Shift> roster;
    private double salary = 0;
    private double hWage = 3.75;
    private double increment = 1;
    private int overtimeThresh = 8;
    private double incrementMult = 0.25;

    //This is where the wage is calculated
    public WageCalculator(List roster) {
        this.roster = roster;
    }

    public void processRoster() {
        for (int i = 0; i < this.roster.size(); i++) {
            double start = convertToHours(this.roster.get(i).getStart());
            double end = convertToHours(this.roster.get(i).getEnd());

            calculateShiftWage(start, end);
        }
    }

    private void calculateShiftWage(double start, double end) {
        wageModifier((int) Math.floor(start));
        this.salary += calculateFractionWage(start, end);

        int loopStart = (int) Math.ceil(start);
        int loopEnd = (int) Math.floor(end);

        for (int i = loopStart; i < loopEnd; i++) {
            wageModifier(i);
            incrementModifier(loopStart, i);
            this.salary += this.hWage * this.increment;
        }

        resetModifiers();
    }

    public double getSalary() {
        return this.salary;
    }

    //This method converts minutes to hours. So if start time is 16:30 it will be changed to 16.5
    private double convertToHours(String start) {
        String[] numbers = start.split(":");
        double hours = Integer.parseInt(numbers[0]);
        hours += Integer.parseInt(numbers[1]) / 60;

        return hours;
    }

    //This method calculates the wage for minutes, not hours.
    private double calculateFractionWage(double start, double end) {
        double palkka = 0;

        if (Math.abs(start - Math.ceil(start)) != 0) {
            wageModifier((int) start);
            palkka += Math.abs(start - Math.floor(start)) * this.hWage;
        }

        if (Math.abs(end - Math.ceil(end)) != 0) {
            wageModifier((int) end);
            fractionIncrementModifier(start, end);
            palkka += Math.abs(end - Math.floor(end)) * this.hWage * this.increment;
        }

        return palkka;
    }

    private void wageModifier(int hour) {
        if (hour <= 5 || hour >= 18) {
            this.hWage = 4.9;

        } else {
            this.hWage = 3.75;
        }
    }

    //Checks if increment should be risen.
    private void incrementModifier(int loopStart, int i) {
        if (loopStart - i == this.overtimeThresh) {
            this.increment = this.incrementMult + 1;
            this.overtimeThresh += 2;
            this.incrementMult *= 2;
        }
    }

    private void fractionIncrementModifier(double start, double end) {
        if (Math.abs(start - end) >= 12) {
            this.increment = 2;

        } else if (Math.abs(start - end) >= 10) {
            this.increment = 1.5;

        } else if (Math.abs(start - end) >= 8) {
            this.increment = 1.25;
        }

        resetModifiers();
    }

    private void resetModifiers() {
        this.hWage = 3.75;
        this.increment = 1;
        this.overtimeThresh = 8;
        this.incrementMult = 0.25;
    }
}
