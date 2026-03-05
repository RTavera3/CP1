/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package motorph;

/**
 *
 * @author ryan.tavera
 */
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;

public class MotorPH {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String filePath = "/Users/ryan.tavera/NetBeansProjects/MotorPH/resources/MotorPH_Employee Data - Employee Details.csv";
         
        
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            
            
            String[] row;
            
            reader.readNext(); // skip header row
            
            while ((row = reader.readNext()) != null) {
                
                String employeeId   = row[0];
                String lastName     = row[1];
                String firstName    = row[2];
                String Birthday    = row[3];
                String position     = row[11];
                
                
                // Remove commas on the output row[13] and [18] so Java can parse the numbers
                String basicSalaryStr = row[13].replace(",", "");
                double basicSalary = Double.parseDouble(basicSalaryStr);
                
                String hourlyRateStr = row[18].replace(",", "");
                double hourlyRate = Double.parseDouble(hourlyRateStr);

                // Print formatted output
                System.out.printf("%s - %s %s | %s | %s | Salary: %.2f | Hourly Rate: %.2f%n",
                    row[0], row[2], row[1], row[3], row[11], basicSalary, hourlyRate
                );
              
            
            } 
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    
        
        
    }
    
}
