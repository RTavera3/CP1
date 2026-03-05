/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package motorph;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class MotorPH {

    public static void main(String[] args) {
        String employeeFile = "/Users/ryan.tavera/NetBeansProjects/MotorPH/resources/MotorPH_Employee Data - Employee Details.csv";
        String attendanceFile = "/Users/ryan.tavera/NetBeansProjects/MotorPH/resources/MotorPH_Employee Data - Attendance Record.csv";

        Scanner input = new Scanner(System.in);

        // LOGIN
        System.out.print("Enter Username: ");
        String username = input.nextLine();
        System.out.print("Enter Password: ");
        String password = input.nextLine();

        if (!(username.equals("employee") || username.equals("payroll_staff")) || !password.equals("12345")) {
            System.out.println("Incorrect username and/or password.");
            System.out.println("Program terminated.");
            input.close();
            return;
        }

        System.out.println("Login successful!");

        if (username.equals("employee")) {
            employeeMenu(input, employeeFile);
        } else {
            payrollStaffMenu(input, employeeFile, attendanceFile);
        }

        input.close();
    }

    // ===========================
    // Employee Menu
    // ===========================
    public static void employeeMenu(Scanner input, String employeeFile) {
        System.out.println("\nEmployee Menu");
        System.out.println("1. Enter your employee number");
        System.out.println("2. Exit");
        System.out.print("Choose option: ");
        int choice = input.nextInt();
        input.nextLine(); // consume leftover newline

        if (choice == 1) {
            System.out.print("Enter your employee number: ");
            String empNumber = input.nextLine();  // Here you type 10002
            String[] empData = getEmployeeData(employeeFile, empNumber);
            if (empData == null) {
                System.out.println("Employee number does not exist.");
            } else {
                System.out.println("\nEmployee Details:");
                System.out.println("Employee #: " + empData[0]);
                System.out.println("Employee Name: " + empData[2] + " " + empData[1]);
                System.out.println("Birthday: " + empData[3]);
            }
        } else {
            System.out.println("Program terminated.");
        }
    }

    // ===========================
    // Payroll Staff Menu
    // ===========================
    public static void payrollStaffMenu(Scanner input, String employeeFile, String attendanceFile) {
        System.out.println("\nPayroll Menu");
        System.out.println("1. Process Payroll");
        System.out.println("2. Exit");
        System.out.print("Choose option: ");
        int payrollChoice = input.nextInt();
        input.nextLine(); // consume leftover newline

        if (payrollChoice == 1) {
            System.out.println("\nProcess Payroll");
            System.out.println("1. One employee");
            System.out.println("2. All employees");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            int option = input.nextInt();
            input.nextLine(); // consume leftover newline

            if (option == 1) {
                System.out.print("Enter employee number: ");
                String empNumber = input.nextLine();
                processPayrollForOne(employeeFile, attendanceFile, empNumber);
            } else if (option == 2) {
                processPayrollForAll(employeeFile, attendanceFile);
            } else {
                System.out.println("Program terminated.");
            }
        } else {
            System.out.println("Program terminated.");
        }
    }

    // ===========================
    // Process Payroll for One Employee
    // ===========================
    public static void processPayrollForOne(String employeeFile, String attendanceFile, String empNumber) {
    String[] empData = getEmployeeData(employeeFile, empNumber);
    if (empData == null) {
        System.out.println("Employee number does not exist.");
        return;
    }

    System.out.println("\nEmployee #: " + empData[0]);
    System.out.println("Employee Name: " + empData[2] + " " + empData[1]);
    System.out.println("Birthday: " + empData[3]);

    ArrayList<String[]> attendanceList = readAttendance(attendanceFile, empNumber);
    if (attendanceList.isEmpty()) {
        System.out.println("No attendance records found.");
        return;
    }

    double hourlyRate = 500; // Example hourly rate

    // Process all attendance records June to December
    // Group by month and cutoff
    String currentMonth = "";
    double cutoffHours = 0;
    double cutoffGross = 0;
    ArrayList<Double> firstCutoffGrossList = new ArrayList<>();

    for (String[] record : attendanceList) {
        String date = record[3]; // Correct date column
        String logIn = record[4];
        String logOut = record[5];

        // Parse month and day
        String[] dateParts = date.split("/"); // MM/DD/YYYY
        String month = dateParts[0];
        int day = Integer.parseInt(dateParts[1]);

        double hours = calculateHours(logIn, logOut);
        double gross = hours * hourlyRate;

        // First cutoff: day 1–15
        if (day <= 15) {
            System.out.println("\nCutoff Date: " + month + "/1 to " + month + "/15");
            System.out.printf("Total Hours Worked: %.2f%n", hours);
            System.out.printf("Gross Salary: %.2f%n", gross);

            // Keep first cutoff for second cutoff calculation
            firstCutoffGrossList.add(gross);
        } else { // Second cutoff: day 16–end
            System.out.println("\nCutoff Date: " + month + "/16 to " + month + "/30");
            System.out.printf("Total Hours Worked: %.2f%n", hours);
            System.out.printf("Gross Salary: %.2f%n", gross);

            // Total gross for deductions = first + second cutoff
            double totalGrossForDeductions = gross;
            for (double g : firstCutoffGrossList) totalGrossForDeductions += g;

            // Government deductions
            double sss = computeSSS(totalGrossForDeductions);
            double philhealth = computePH(totalGrossForDeductions);
            double pagibig = computePagibig(totalGrossForDeductions);
            double tax = computeIncomeTax(totalGrossForDeductions);
            double totalDeductions = sss + philhealth + pagibig + tax;

            double netSalary = gross - totalDeductions;

            System.out.println("Each Deduction:");
            System.out.println("SSS: " + sss);
            System.out.println("PhilHealth: " + philhealth);
            System.out.println("Pag-IBIG: " + pagibig);
            System.out.println("Tax: " + tax);
            System.out.println("Total Deductions: " + totalDeductions);
            System.out.println("Net Salary: " + netSalary);

            // Clear first cutoff list for next month
            firstCutoffGrossList.clear();
        }
    }
    }

    // ===========================
    // Process Payroll for All Employees
    // ===========================
    public static void processPayrollForAll(String employeeFile, String attendanceFile) {
        ArrayList<String[]> employees = readAllEmployees(employeeFile);
        for (String[] emp : employees) {
            processPayrollForOne(employeeFile, attendanceFile, emp[0]);
            System.out.println("\n-----------------------------");
        }
    }

    // ===========================
    // Read Employee Data
    // ===========================
    public static String[] getEmployeeData(String employeeFile, String empNumber) {
        try (CSVReader reader = new CSVReader(new FileReader(employeeFile))) {
            String[] line;
            reader.readNext(); // skip header
            while ((line = reader.readNext()) != null) {
                if (line[0].equals(empNumber)) return line;
            }
        } catch (IOException | CsvValidationException e) {
            System.out.println("Error reading employee file.");
        }
        return null;
    }

    public static ArrayList<String[]> readAllEmployees(String employeeFile) {
        ArrayList<String[]> list = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(employeeFile))) {
            String[] line;
            reader.readNext(); // skip header
            while ((line = reader.readNext()) != null) list.add(line);
        } catch (IOException | CsvValidationException e) {
            System.out.println("Error reading employee file.");
        }
        return list;
    }

    public static ArrayList<String[]> readAttendance(String attendanceFile, String empNumber) {
        ArrayList<String[]> list = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(attendanceFile))) {
            String[] line;
            reader.readNext(); // skip header
            while ((line = reader.readNext()) != null) {
                if (line[0].equals(empNumber)) list.add(line);
            }
        } catch (IOException | CsvValidationException e) {
            System.out.println("Error reading attendance file.");
        }
        return list;
    }

    // ===========================
    // Calculate Hours Worked (8AM to 5PM only)
    // ===========================
    public static double calculateHours(String logIn, String logOut) {
        try {
            LocalTime in = LocalTime.parse(logIn);
            LocalTime out = LocalTime.parse(logOut);
            LocalTime startTime = LocalTime.of(8, 0);
            LocalTime endTime = LocalTime.of(17, 0);

            if (in.isBefore(startTime)) in = startTime;
            if (out.isAfter(endTime)) out = endTime;

            Duration duration = Duration.between(in, out);
            return duration.toMinutes() / 60.0;
        } catch (Exception e) {
            return 0.0;
        }
    }

    // ===========================
    // Government & Tax Calculations
    // ===========================
    public static double computePH(double salary) {
        double PHpremium;
        if (salary <= 10000) PHpremium = 300;
        else if (salary >= 60000) PHpremium = 1800;
        else PHpremium = salary * 0.03;
        return PHpremium * 0.5;
    }

    public static double computeSSS(double salary) {
        if (salary < 3250) return 135;
        else if (salary <= 3750) return 157.5;
        else if (salary <= 4250) return 180;
        else if (salary <= 4750) return 202.5;
        else if (salary <= 5250) return 225;
        else if (salary <= 5750) return 247.5;
        else if (salary <= 6250) return 270;
        else if (salary <= 6750) return 292.5;
        else if (salary <= 7250) return 315;
        else if (salary <= 7750) return 337.5;
        else if (salary <= 8250) return 360;
        else if (salary <= 8750) return 382.5;
        else if (salary <= 9250) return 405;
        else if (salary <= 9750) return 427.5;
        else if (salary <= 10250) return 450;
        else if (salary <= 10750) return 472.5;
        else if (salary <= 11250) return 495;
        else if (salary <= 11750) return 517.5;
        else if (salary <= 12250) return 540;
        else if (salary <= 12750) return 562.5;
        else if (salary <= 13250) return 585;
        else if (salary <= 13750) return 607.5;
        else if (salary <= 14250) return 630;
        else if (salary <= 14750) return 652.5;
        else if (salary <= 15250) return 675;
        else if (salary <= 15750) return 697.5;
        else if (salary <= 16250) return 720;
        else if (salary <= 16750) return 742.5;
        else if (salary <= 17250) return 765;
        else if (salary <= 17750) return 787.5;
        else if (salary <= 18250) return 810;
        else if (salary <= 18750) return 832.5;
        else if (salary <= 19250) return 855;
        else if (salary <= 19750) return 877.5;
        else if (salary <= 20250) return 900;
        else if (salary <= 20750) return 922.5;
        else if (salary <= 21250) return 945;
        else if (salary <= 21750) return 967.5;
        else if (salary <= 22250) return 990;
        else if (salary <= 22750) return 1012.5;
        else if (salary <= 23250) return 1035;
        else if (salary <= 23750) return 1057.5;
        else if (salary <= 24250) return 1080;
        else if (salary <= 24750) return 1102.5;
        else return 1125;
    }

    public static double computePagibig(double salary) {
        double cappedSalary = salary > 5000 ? 5000 : salary;
        double rate = cappedSalary <= 1500 ? 0.01 : 0.02;
        return cappedSalary * rate;
    }

    public static double computeIncomeTax(double taxableIncome) {
        if (taxableIncome <= 20832) return 0;
        else if (taxableIncome <= 33332) return (taxableIncome - 20833) * 0.20;
        else if (taxableIncome <= 66666) return 2500 + (taxableIncome - 33333) * 0.25;
        else if (taxableIncome <= 166666) return 10833 + (taxableIncome - 66667) * 0.30;
        else if (taxableIncome <= 666666) return 40833.33 + (taxableIncome - 166667) * 0.32;
        else return 200833.33 + (taxableIncome - 666667) * 0.35;
    }
}