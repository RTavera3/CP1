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
    
    private String employeeNumber;
    private String lastName;
    private String firstName;
    private String birthday;
    
    
    // Constructor
    public Employee(String employeeNumber, String lastName, String firstName, String birthday) {
        this.employeeNumber = employeeNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getBirthday() {
        return birthday;
    }
    
   
    
}
