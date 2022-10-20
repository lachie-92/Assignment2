package Menu;

import Models.Status;
import Models.Ticket;

public class StatusMenu {
	
	public static void menu(Models.App App, Ticket ticket) {
		int menu_selection = 0;
		boolean valid_selection = false;
		
		do {
			// Print Options
			print_menu();
			
			menu_selection = App.scanner.nextInt();
			// Finish buffering the next int.
			App.scanner.nextLine();
			
			if(menu_selection >= 0 && menu_selection <= Status.values().length) { 
				valid_selection = true;
			}
			else {
				System.out.println("This is not a valid option");
			}
			
			
		} while( valid_selection == false);
		// Exit to previous menu
		if(menu_selection == 0) {
			return;
		}
		
		// Set ticket status
		ticket.setStatus(Status.values()[menu_selection - 1]);
		
		return;
	}
	
	private static void print_menu() {
		System.out.println();
		
		System.out.println("----------------------");
		System.out.println("Change Ticket Status");
		System.out.println("----------------------");
		System.out.println("(1) Open Ticket");
	    System.out.println("(2) Close and Resolve");
	    System.out.println("(3) Closed and Unresolved");	    
	    System.out.println("(0) Return to previous menu");
	    System.out.println("----------------------");
	    System.out.print("Please enter an option :");
	    
		return;
	}

}
