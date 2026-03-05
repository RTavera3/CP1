/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motorph;

import java.util.Scanner;
/**
 *
 * @author ryan.tavera
 */
public class GovernmentCalculator {
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
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter monthly net salary: ");
        double grossSalary = scanner.nextDouble();
        
        
        double sss = computeSSS(grossSalary);
        double philhealth = computePH(grossSalary);
        double pagibig = computePH(grossSalary);
        
        double taxableIncome = grossSalary - sss - philhealth - pagibig;
        double incomeTax = computeIncomeTax(taxableIncome);

        double employeeShare = grossSalary / 50;
        
        double netSalary = grossSalary - sss - philhealth - pagibig - incomeTax - employeeShare;

        System.out.println("\n===== MOTORPH PAYROLL SYSTEM =====");
        System.out.println("Gross Salary: " + grossSalary);
        System.out.println("SSS: " + sss);
        System.out.println("PhilHealth: " + philhealth);
        System.out.println("Pag-IBIG: " + pagibig);
        System.out.println("Income Tax: " + incomeTax);
        System.out.println("Employee Share (50%): " + employeeShare);
        System.out.println("-----------------------------------");
        System.out.println("Net Salary: " + netSalary);

        scanner.close();
    }
}
    
