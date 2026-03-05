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
public class CalculatehourWorked {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

       
        System.out.print("Enter number of hours worked: ");
        int hoursWorked = input.nextInt();

        // conditional statements
        if (hoursWorked < 0) {
            System.out.println("Invalid input.");
        }
        else if (hoursWorked == 0) {
            System.out.println("Employee did not work this week.");
        }
        else if (hoursWorked >= 1 && hoursWorked <= 40) {
            System.out.println("Regular work hours.");
        }
        else if (hoursWorked > 40) {
            System.out.println("Employee has overtime.");
        }

        input.close();
    }
}

