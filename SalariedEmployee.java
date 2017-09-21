import java.io.Serializable;
//A SalariedEmployee class is use to calculate and store the information of empolyee's annual wage and other information
public class SalariedEmployee extends Employee implements Serializable{
	
	private double annualSalary;	
	private double weeklyPay;

	//A constructor for SalariedEmployee that pass employeename and annualsalary as parameters
	public SalariedEmployee ( String employeeName, double annualSalary){
		
		//call the parent class and converted the annualSalary to the hourly wage by divide the 52 weeks and 40 hours
		super(employeeName,(annualSalary/52/40));
		this.annualSalary = annualSalary;
	}
	
	//getter that access the annual Salary
	public double getAnnualSalary(){

		return annualSalary;
	} 

	//setter that pass the annual Salary in to the class
	public void setAnnualSalary(double annualSalary){
	
		this.annualSalary = annualSalary;
	}

	//toString that convert all the information in format for dispaly reason 
	public String toString(){

		String str = getEmployeeName() + toDollars(getAnnualSalary()) + "/year";
		String space = " ";
		for(int i = 0; i<(40 - str.length()); i++){
			space += " ";
		}
		str = getEmployeeName() + space  + "$" + toDollars(getAnnualSalary()) + "/year";
		return str;
		
			
	}
	
	//calculate the weekly wage regardless of the number of hours worked
	public double computePay(int hours){
		
		return	getAnnualSalary()/52;
	}

	//toDollars method converts all the double values to two decimal places
 	 public static String toDollars(double amount) {
   	
		 long roundedAmount = Math.round(amount * 100);
   		 long dollars = roundedAmount / 100;
  		  long cents = roundedAmount % 100;
  		  if (cents <= 9)
		      return dollars + ".0" + cents;
		    else
		      return dollars + "." + cents;
  	}

}
