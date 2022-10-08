package Menu;

import java.util.Scanner;
import Models.App;

public class Technician {
	
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
				case 1 :  System.out.println("Option 1 selected."); break;
				case 2 :  System.out.println("Option 2 selected."); break;
				case 3 :  System.out.println("Option 3 selected."); break;
				case 0 :  System.out.println("Returning to main menu."); break;
				default : System.out.println("That is not a valid option. Please select again");
			}
		} while(menu_selection != 0);
		
		return;   
	}
	
	/* Prints the Technician Menu */
	private static void print_menu() {
		System.out.print("\r\n");
		System.out.println("----------------------");
		System.out.println("Technician Menu");
        System.out.println("----------------------");
        System.out.println("(1) View assigned tickets.");
        System.out.println("(2) View all closed tickets.");
        System.out.println("(3) View closed tickets.");
        System.out.println("(0) Return to main menu");
        System.out.println("----------------------");
        System.out.print("Please select an option :");
	}

}
