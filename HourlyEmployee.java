import java.io.Serializable;
//A hourlyEmployee class is use to calculate and store the information of empolyee's hourly wage and other information
public class HourlyEmployee extends Employee implements Serializable{
	
	private double weeklyPay;
	
	//constructor for Hourly Employee which pass the Employee name and hourly wage
	public HourlyEmployee (String employeeName, double hourlyWage){
		
		//call the employee class constructor
		super(employeeName, hourlyWage);
	}

	//computePay method will return a number of weekly salary regards to the working hours 
	public double computePay (int hours){
		
		if(hours <= 40)
			weeklyPay = hours * getHourlyWage();

		else if(hours > 40){
			weeklyPay = (40 * getHourlyWage()) + (((hours - 40) * 1.5) * getHourlyWage());
		}
		return weeklyPay;
	}
	
	//toString method that return the employee name and hourlywage in format
	public String toString (){
	
		String str = getEmployeeName() + toDollars(getHourlyWage()) + "/hour";
		String space = " ";
		for(int i = 0; i<(40 - str.length()); i++){
			space += " ";
		}
		str = getEmployeeName() + space  + "$" + toDollars(getHourlyWage()) + "/hour";
		return str;

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
