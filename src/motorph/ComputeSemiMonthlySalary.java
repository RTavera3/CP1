package motorph;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ComputeSemiMonthlySalary {

    public static void main(String[] args) {
        String filePath = "/Users/ryan.tavera/NetBeansProjects/MotorPH/resources/MotorPH_Employee Data - Employee Details.csv";
        List<Employee> employees = new ArrayList<>();

        // Step 1: Load employees from CSV
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] row;
            reader.readNext(); // skip header

            while ((row = reader.readNext()) != null) {
                String employeeId = row[0];
                String lastName = row[1];
                String firstName = row[2];
                String position = row[11];
                double basicSalary = Double.parseDouble(row[13].replace(",", ""));
                double hourlyRate = Double.parseDouble(row[18].replace(",", ""));

                employees.add(new Employee(employeeId, firstName, lastName, position, basicSalary, hourlyRate));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            return;
        }

        Scanner scanner = new Scanner(System.in);

        // Step 2: Ask for employeeId
        System.out.print("Enter Employee ID: ");
        String inputId = scanner.nextLine();

        // Step 3: Find the employee
        Employee emp = null;
        for (Employee e : employees) {
            if (e.getEmployeeId().equals(inputId)) {
                emp = e;
                break;
            }
        }

        if (emp == null) {
            System.out.println("Error: Employee ID not found!");
        } else {
            // Step 4: Ask for hours worked only
            System.out.print("Enter total hours worked in cutoff period: ");
            double hoursWorked = scanner.nextDouble();

            if (hoursWorked <= 0) {
                System.out.println("Error: Hours worked must be positive!");
            } else {
                // Step 5: Compute semi-monthly salary
                double semiMonthlySalary = hoursWorked * emp.getHourlyRate();

                // Step 6: Display results
                System.out.println("\n--- Semi-Monthly Salary Computation ---");
                System.out.printf("Employee: %s %s (ID: %s)%n",
                                  emp.getFirstName(), emp.getLastName(), emp.getEmployeeId());
                System.out.printf("Position: %s%n", emp.getPosition());
                System.out.printf("Hours Worked: %.2f%n", hoursWorked);
                System.out.printf("Hourly Rate: %.2f%n", emp.getHourlyRate());
                System.out.printf("Semi-Monthly Salary: %.2f%n", semiMonthlySalary);
                System.out.println("Computation verified successfully!");
            }
        }

        scanner.close();
    }
}
