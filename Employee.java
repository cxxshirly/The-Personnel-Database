import java.io.Serializable;
//A abstract class that store employee name and hourlywage.
public abstract class Employee implements Serializable{

	private String employeeName;
	private double hourlyWage;

	//a protected constructor for employee to pass in employee name and hourlywage
	protected Employee(String employeeName, double hourlyWage){
	
		this.employeeName = employeeName;
		this.hourlyWage = hourlyWage;
	}	
	
	//an abstract method that computes the weekly pay for the employees
	public abstract double computePay(int hours);

	//setter for Employee name
	public void setEmployeeName(String employeeName){
		
		this.employeeName = employeeName;
	}
	
	//set the hourlywage
	public void setHourlyWage(double hourlyWage){
		
		this.hourlyWage = hourlyWage;
	}
	
	//getter that return employeeName
	public String getEmployeeName(){
		
		return employeeName;
	}	

	//get the hourly wage
	public double getHourlyWage(){

		return hourlyWage;
	}

	//a method that increases the hourly wage by a given percentage
	public void raiseWage(double percentage){

		hourlyWage = hourlyWage + (hourlyWage *( percentage * 0.01));
		

	}




}
