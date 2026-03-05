/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motorph;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author ryan.tavera
 */
public class ReadFromTextFile {
    
    
    public static double computePH(double salary) {

        double PHpremium = salary * 0.03;

        if (salary <= 10000) {
            PHpremium = 300;
        } 
        else if (salary >= 60000) {
            PHpremium = 1800;
        }

        return PHpremium;
    }
    
    public static double computeSSS(double salary) {

        double maxMSC = 30000;
        double employeeRate = 0.045;

        if (salary > maxMSC) {
        salary = maxMSC;
        }

        return salary * employeeRate;
    }
        
    public static double computePagibig(double salary) {
        double maxCompensation = 5000;
        double rate = 0.02;

        if (salary > maxCompensation) {
        salary = maxCompensation;
    }

        return salary * rate;
    }
        
    public static double computeIncomeTax(double taxableIncome) {

        if (taxableIncome <= 20833) {
        return 0;
        } 
        else if (taxableIncome <= 33333) {
            return (taxableIncome - 20833) * 0.15;
        } 
        else if (taxableIncome <= 66667) {
            return 1875 + (taxableIncome - 33333) * 0.20;
        } 
        else if (taxableIncome <= 166667) {
            return 8541.80 + (taxableIncome - 66667) * 0.25;
        } 
        else if (taxableIncome <= 666667) {
            return 33541.80 + (taxableIncome - 166667) * 0.30;
        } 
        else {
            return 183541.80 + (taxableIncome - 666667) * 0.35;
        }
    }
    
    public static void main(String[] args) {
        // Path to my file
        String filePath = "/Users/ryan.tavera/NetBeansProjects/MotorPH/resources/employee_data.txt";
        
        try {
            // Step 1: Create FileReader
            FileReader fr = new FileReader(filePath);
            // Step 2: Wrap FileReader with BufferedReader for efficient reading
            BufferedReader br = new BufferedReader(fr);
            
            String line;
            // Step 3: Read file line by line
            while ((line = br.readLine()) != null) {
                // Split the line by comma
                String[] parts = line.split(",");
                
                // Parse employee name
                String name = parts[0].trim();
                
                // Parse gross salary
                double grossSalary = Double.parseDouble(parts[1].trim());
                
                // Compute deductions
                double sss = computeSSS(grossSalary);
                double ph = computePH(grossSalary);
                double pagibig = computePagibig(grossSalary);
                
                // Taxable income = gross salary - mandatory deductions
                double taxableIncome = grossSalary - (sss + ph + pagibig);
                
                // Compute income tax
                double incomeTax = computeIncomeTax(taxableIncome);
                
                // Net salary = gross - all deductions
                double netSalary = grossSalary - (sss + ph + pagibig + incomeTax);

                
                // Print Employee Details and Overall Compensation
                System.out.println("Employee Name: " + name);
                System.out.println("Gross Salary: " + grossSalary);
                System.out.println("SSS: " + sss);
                System.out.println("PhilHealth: " + ph);
                System.out.println("Pag-IBIG: " + pagibig);
                System.out.println("Income Tax: " + taxableIncome);
                System.out.println("Net Pay: " + netSalary);
                System.out.println("-------------------------------");
                
            }

            // Step 4: Close the BufferedReader
            br.close();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Salary format error: " + e.getMessage());
        }
        
        
       
        
    }
    
}
