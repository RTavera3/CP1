/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motorph;

import java.time.LocalTime;
import java.time.Duration;

/**
 *
 * @author ryan.tavera
 */
public class Attendance {
    
    private String employeeId;
    private String lastName;
    private String firstName;
    private String date;
    private String logIn;
    private String logOut;
    
    public Attendance(String employeeId, String lastName, String firstName,
                      String date, String logIn, String logOut) {

        this.employeeId = employeeId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.date = date;
        this.logIn = logIn;
        this.logOut = logOut;
    
    }
    
    public String getEmployeeId() {
        return employeeId;
    }

    public String getDate() {
        return date;
    }

    public String getLogIn() {
        return logIn;
    }

    public String getLogOut() {
        return logOut;
    }
    
    
    // Calculate hours worked
    public double getHoursWorked() {

        LocalTime in = LocalTime.parse(logIn);
        LocalTime out = LocalTime.parse(logOut);

        Duration duration = Duration.between(in, out);

        return duration.toMinutes() / 60.0;
    }

    @Override
    public String toString() {
        return employeeId + " | " + firstName + " " + lastName +
                " | " + date +
                " | In: " + logIn +
                " | Out: " + logOut +
                " | Hours: " + String.format("%.2f", getHoursWorked());
    }
    
    
}
