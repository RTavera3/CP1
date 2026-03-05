/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * This is for Task 7 Week 6 Module
 * @author ryan.tavera
 */
public class CalculateHoursWorked {
    
    public static void main(String[] args) {
        // Sample employee info
        String employeeName = "Manuel III Garcia";
        
        // Step 1: Time in, time out, break
        double timeIn = 8.0;    // 8:00 AM
        double timeOut = 17.0;  // 5:00 PM
        double breakTime = 1.0; // 1 hour lunch break\
        
        // Step 2: Calculate total hours worked
        double totalHoursWorked = (timeOut - timeIn) - breakTime;
        
        // Step 3: Display the result with clear labels
        System.out.println("Employee Name: " + employeeName);
        System.out.println("Total Hours Worked: " + totalHoursWorked + " hours");
        
        // Step 4: Verification
        double expectedHours = 8.0;
        
        if (totalHoursWorked == expectedHours){
            System.out.println("Test Passed: Computation is correct!");
        }
        else System.out.println("Test failed: Computation is incorrect!");{
        
        
        }
            
            
            
    }
    
}
