package Auth;
import Models.App;

import Models.User;

public class Register {

	/* Creates a new staff member in the system */
	public static void RegisterNewUser(App App) {
		
		System.out.println("Register new staff member");
        System.out.println("----------------------");
        
        // Request users name
		System.out.println("Please enter the staff members full name");
		String name = App.scanner.nextLine();
		
		// Request users email
		System.out.println("Please enter the staff members email address");
		String email = App.scanner.nextLine();

		
		// Request users email
		System.out.println("Please enter the staff members phone number");
		String phone = App.scanner.nextLine();
		
		// Request users email
		System.out.println("Please enter the staff members password");
		String password = App.scanner.nextLine();
		
		// Create new User
		User user = new User(App.getUsers().size(), name, email, phone, password, "Staff", 0);
		
		// Store ticket in Global Tickets List
		App.storeNewUser(user);
		
		System.out.println("An account for "+ user.getName() + " has now been created.");
		
		return;
	}
	
}
