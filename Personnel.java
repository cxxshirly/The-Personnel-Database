import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
//main class that maintains wage information for the employees of a  company.
public class Personnel implements Serializable{
i
	public static void main (String[] args){
		//A arraylist that hold all the records for this program
		ArrayList <Employee> records = new ArrayList (10);
		Scanner scr = new Scanner(System.in);
		String choice = "";
		displayMenu();
		System.out.print("Enter command: ");
		choice = scr.nextLine();
		//convert all the choice to lower case 
		choice = choice.toLowerCase();
		//A while loop that make sure the program will only end when the user press "q"
		while(choice.toLowerCase().compareTo(("q"))!= 0){	
			switch(choice){
				case "n":
					newEmployee(records);				
					break;
				case "c":
					computePaychecks(records);
					break;
				case "r":
					raiseWages(records);		
					break;
				case "p":
					printRecords(records);
					break;
				case "d":
					downloadData(records);
					break;
				case "u":
					uploadData(records);	
					break;
				case "q":
					System.out.println("Thank you! Have a nice day!");	
		
					break;
				default:
					System.out.println();
					System.out.println("Command was not recognized; please try again.");
				
			}
			displayMenu();
			System.out.print("Enter command: ");
			choice = scr.nextLine();			
			choice = choice.toLowerCase();
			isInputVaild(choice);				
		}

	}

	//A displayMenu that show the menu option for user
	public static void displayMenu (){
		System.out.println("--------------------------------------");
		System.out.println("|Commands:     n - New employee	     |");			
		System.out.println("|	       c - Compute paychecks |");
		System.out.println("| 	       r - Raise wages	     |");
		System.out.println("|	       p - Print record      |");
		System.out.println("| 	       d - Download data     |");
		System.out.println("| 	       u - Upload data 	     |");
	        System.out.println("| 	       q - Quit		     |");
	        System.out.println("--------------------------------------");
	}
	
	//A method that return a string when the input is not vaild
	public static String isInputVaild(String input){
		String[] choice = {"n","c","r","p","d","u","q"};
		input = input.toLowerCase();
		//check the choice is vaild or not
		for(int i =0; i<choice.length;i++){
			if(input.equals(choice[i])){
				return choice[i];
			}
		}

		return "Command was not recognized; please try again.\n";
	}
	
	//A mewEmployee method helps the user to creat new employee object once a time
	public static void newEmployee(ArrayList<Employee> newEmployee){

		Scanner input = new Scanner(System.in);
		System.out.print("Enter the name of new employee: ");
		String employeeName = input.nextLine();
		System.out.print("Hourly (h) or salaried (s): ");
		String type = input.nextLine();
		//user must enter "h" or "s" otherwise will returned an Error message
		if(type.equals("h") || type.equals("H")){
			System.out.print("Enter hourly wage: ");
			//the user must enter a double vaule else the system with sent out an Error message
			if(input.hasNextDouble() == true){
				double hourlyWage = input.nextDouble();
				newEmployee.add(new HourlyEmployee(employeeName,hourlyWage));
			}
			else{
				 System.out.println("Error: the employee's wage must be entered as a number.");			
			}
		}
		else if(type.equals("s") || type.equals("S")){
			System.out.print("Enter annual salary: ");
			//the user must enter a double vaule else the system with sent out an Error message
			if(input.hasNextDouble() == true){
				double annualSalary = input.nextDouble();
				newEmployee.add(new SalariedEmployee(employeeName,annualSalary));
			}
			else{
				 System.out.println("Error: the employee's wage must be entered as a number.");
			}
		}
		else{
			System.out.println("Input was not h or s; please try again.\n");
		}	
	}
	
	//CompoyePayChecks method helps user to compute paycheck according to the hours they enter
	public static void computePaychecks(ArrayList<Employee> newEmployee){
		
		Scanner scrC = new Scanner(System.in);
		for(int i = 0; i < newEmployee.size(); i++){
			System.out.print("Enter number of hours worked by " + newEmployee.get(i).getEmployeeName() + " :");
			//check if the user enter an int value for hours
			if(scrC.hasNextInt() == true){
				int hours = scrC.nextInt();
				System.out.print("Pay:  $" + toDollars( newEmployee.get(i).computePay(hours))+ " \n"); 
			}
			else{
				 System.out.println("Error: the employee's wage must be entered as a number.");
				 break;
			}
			
		}
		System.out.println();
	}
	
	//a raise Wage method that rasie employees' wages by enter the percentage
	public static void raiseWages(ArrayList<Employee> newEmployee){
		
		Scanner scrR = new Scanner(System.in);
		System.out.print("Entere percentage increase: ");
		//check if the user enter an double value here
		if(scrR.hasNextDouble() == true){
			double percentage = scrR.nextDouble();
			System.out.print("\nNew Wages\n");
			System.out.println("----------");
			//print out the wages for all the emoloyees
			for(int i = 0; i < newEmployee.size(); i++){
				newEmployee.get(i).raiseWage(percentage);	
				//instance of help the program to see the difference between hourly employee and salaried employee
				if(newEmployee.get(i) instanceof HourlyEmployee){
					System.out.println(newEmployee.get(i).toString());
				}
				else{
					((SalariedEmployee)newEmployee.get(i)).setAnnualSalary(newEmployee.get(i).getHourlyWage()*52*4);
					System.out.println(newEmployee.get(i).toString());
				}
			}
		}
		else System.out.println("Error in percent increase; Please try again: ");

	}
	
	//Print out all the employees records using for loops
	public static void printRecords(ArrayList<Employee> newEmployee){

		for(int i = 0; i < newEmployee.size(); i++){
			System.out.println(newEmployee.get(i).toString());
		}	
	}
	
	//download the employee data to the employee.dat file for futher use	
	public static void downloadData(ArrayList<Employee> newEmployee){

		System.out.println("Now downloading these records..");
		printRecords(newEmployee);
		String fileName = "employee.dat";
		try{
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
		 	out.writeObject(newEmployee);
			out.close();
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	//upload the data from the employee.dat to the program
	public static void uploadData(ArrayList <Employee> newEmployee){
		
		String fileName = "employee.dat";
		System.out.println("uploading employee.dat...");
		//a new arraylist that store the data
		ArrayList <Employee> Employee = new ArrayList();
		try {
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Employee = (ArrayList <Employee>) in.readObject();
			//for loop add the data  back to newEmployee arraylist
			for(int i = 0; i < Employee.size(); i++){
				newEmployee.add(Employee.get(i));
			}
			in.close();
			fileIn.close();
			System.out.println("employee.dat loaded");
		}
		catch (IOException e){
			System.out.println(e.getMessage());
		}
		catch (ClassNotFoundException e){
			System.out.println(e.getMessage());
		}
	}
	//toDollars method that convert the double value to two decimal places
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
