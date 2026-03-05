/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motorph;

/**
 *
 * @author ryan.tavera
 */
public class GovernmentandTaxCalculator {
    
    // PhilHealth Contribution (Employee Share) Computation        
    public static double computePH(double salary) {

        double PHpremium;
        
        // Calculate premium based on salary brackets
        if (salary <= 10000) {
            PHpremium = 300;
        } 
        else if (salary >= 60000) {
            PHpremium = 1800;
        }
        else {
            PHpremium = salary * 0.03; // 3% for 10,000.01 – 59,999.99     
        }    
        // Return employee share (50%)
        return PHpremium * 0.5;
    }
    
    // SSS Contribution Computation
    public static double computeSSS(double salary) {
        double contribution;

        if (salary < 3250) contribution = 135;
        else if (salary <= 3750) contribution = 157.5;
        else if (salary <= 4250) contribution = 180;
        else if (salary <= 4750) contribution = 202.5;
        else if (salary <= 5250) contribution = 225;
        else if (salary <= 5750) contribution = 247.5;
        else if (salary <= 6250) contribution = 270;
        else if (salary <= 6750) contribution = 292.5;
        else if (salary <= 7250) contribution = 315;
        else if (salary <= 7750) contribution = 337.5;
        else if (salary <= 8250) contribution = 360;
        else if (salary <= 8750) contribution = 382.5;
        else if (salary <= 9250) contribution = 405;
        else if (salary <= 9750) contribution = 427.5;
        else if (salary <= 10250) contribution = 450;
        else if (salary <= 10750) contribution = 472.5;
        else if (salary <= 11250) contribution = 495;
        else if (salary <= 11750) contribution = 517.5;
        else if (salary <= 12250) contribution = 540;
        else if (salary <= 12750) contribution = 562.5;
        else if (salary <= 13250) contribution = 585;
        else if (salary <= 13750) contribution = 607.5;
        else if (salary <= 14250) contribution = 630;
        else if (salary <= 14750) contribution = 652.5;
        else if (salary <= 15250) contribution = 675;
        else if (salary <= 15750) contribution = 697.5;
        else if (salary <= 16250) contribution = 720;
        else if (salary <= 16750) contribution = 742.5;
        else if (salary <= 17250) contribution = 765;
        else if (salary <= 17750) contribution = 787.5;
        else if (salary <= 18250) contribution = 810;
        else if (salary <= 18750) contribution = 832.5;
        else if (salary <= 19250) contribution = 855;
        else if (salary <= 19750) contribution = 877.5;
        else if (salary <= 20250) contribution = 900;
        else if (salary <= 20750) contribution = 922.5;
        else if (salary <= 21250) contribution = 945;
        else if (salary <= 21750) contribution = 967.5;
        else if (salary <= 22250) contribution = 990;
        else if (salary <= 22750) contribution = 1012.5;
        else if (salary <= 23250) contribution = 1035;
        else if (salary <= 23750) contribution = 1057.5;
        else if (salary <= 24250) contribution = 1080;
        else if (salary <= 24750) contribution = 1102.5;
        else contribution = 1125; // 24,750 and above

        return contribution;
    }
    // Pag-IBIG Contribution Computation    
    public static double computePagibig(double salary) {
        
        double cappedSalary = salary;
        if (cappedSalary > 5000) {
        cappedSalary = 5000; // maximum contribution considered
        }
        
         double rate;
        if (cappedSalary <= 1500) {
        rate = 0.01; // 1% for 1,000–1,500
        } else {
        rate = 0.02; // 2% for over 1,500
        }
        
        return cappedSalary * rate;

    }    
       
}
    
    // Withholding Tax (BIR) Computation
    public static double computeIncomeTax(double taxableIncome) {

        if (taxableIncome <= 20832) {
            return 0;
        } 
        else if (taxableIncome <= 33332) {
            return (taxableIncome - 20833) * 0.20;
        } 
        else if (taxableIncome <= 66666) {
            return 2500 + (taxableIncome - 33333) * 0.25;
        } 
        else if (taxableIncome <= 166666) {
            return 10833 + (taxableIncome - 66667) * 0.30;
        } 
        else if (taxableIncome <= 666666) {
            return 40833.33 + (taxableIncome - 166667) * 0.32;
        } 
        else {
            return 200833.33 + (taxableIncome - 666667) * 0.35;
        }
    }
        
        


    
