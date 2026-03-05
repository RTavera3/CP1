/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motorph; // must match your MotorPH class

/**
 *
 * @author ryan.tavera
 */
public class Employee {
    
    private String employeeId;
    private String firstName;
    private String lastName;
    private String position;
    private double basicSalary;
    private double hourlyRate;
    
    public Employee(String employeeId, String firstName, String lastName,
                    String position, double basicSalary, double hourlyRate) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.basicSalary = basicSalary;
        this.hourlyRate = hourlyRate;
    }
    
    // ---------- Getter Methods ----------
    public String getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    @Override
    public String toString() {
        return employeeId + " - " + firstName + " " + lastName +
               " | " + position +
               " | Salary: " + String.format("%.2f", basicSalary) +
               " | Hourly Rate: " + String.format("%.2f", hourlyRate);
    }
    
}
