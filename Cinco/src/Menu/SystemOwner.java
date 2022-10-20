package Menu;

import Models.App;
import Reports.TicketReport;

public class SystemOwner {

	public static void menu(App App) {

		int menu_selection = 1;
		
		
		do {
			// Print Staff Menu
			print_menu();
			// Request selection from user then buffer scan line
			menu_selection = App.scanner.nextInt();
			App.scanner.nextLine();
			
			// Action selection
			switch(menu_selection) { 
				case 1 :  TicketReport.getReport(App); break;
				case 0 :  App.logout();
						  System.out.println("Logging out and returning to main menu..."); 
						  break;
				default : System.out.println("That is not a valid option. Please select again");
			}
		} while(menu_selection != 0);
	
        return;
	}
	
	/* Prints the Staff Menu */
	private static void print_menu() {
		System.out.print("\r\n");
		System.out.println("----------------------");
		System.out.println("System Operator Menu");
        System.out.println("----------------------");
        System.out.println("(1) Produce Ticket Report.");
        System.out.println("(0) Log Out");
        System.out.println("----------------------");
        System.out.print("Please select an option :");
	}
	
}
