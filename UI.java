

import java.util.Scanner;

public class UI {

	public static Scanner scan = new Scanner(System.in);
	
	// the method to called next line only string
	public static String readLine() {  
		return scan.nextLine(); 
	}
	
	
	// will call only integer/
	public static int readInt() {
		String input = scan.nextLine();
		
		if(tryParseInt(input)) {            // check if its integer or string          
			return Integer.parseInt(input);   //convert string to integer//
		}
		else {
			return -1;
		}
	}	
	
	// check if we put in a Integer or string 
	public static boolean tryParseInt(String input) {
		try {
			Integer.parseInt(input);//takes a string and trys to convert it
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}
	
	public static void emptyList(String type) {
		System.out.println("The " + type + " list is empty");		
	}
	
	public static void badInput() {
		say("Invalid option");
	}
	
	public static void say(String text) { // insted of putint system . out ... every time//
		System.out.println(text);
	}
	
	public static void closeScanner() {
		scan.close();
	}
	
}