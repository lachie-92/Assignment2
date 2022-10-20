package Auth;
import Models.App;
import Models.Role;
import Models.User;

public class Register {

	/* Creates a new staff member in the system */
	public static void RegisterNewUser(App App) {
		Boolean valid_email = false;
		Boolean valid_password = false;
		String password, email;
		
		System.out.println("Register new staff member");
        System.out.println("----------------------");
        
        // Request users name
		System.out.println("Please enter the staff members full name");
		String name = App.scanner.nextLine();
		
		
    	// Prompt user for valid email and validate response.
        do {
        	System.out.println("Please enter the staff members email address:");
        	email = App.scanner.nextLine();
        	// Check to see if user wants to return to main menu
            // Validate Email
        	valid_email = Validator.validateEmail(email);
        } while(valid_email == false);

		
		// Request users email
		System.out.println("Please enter the staff members phone number");
		String phone = App.scanner.nextLine();
		
        // Prompt user for valid password and validate response.
        do {
    		System.out.println("Please enter the staff members password (minimum 20 Alphanumeric Characters):");
        	password = App.scanner.nextLine();
        	// Check to see if user wants to return to main menu
            if(password.equals("0")) return;
            // Validate password
        	valid_password = Validator.validatePassword(password);
        } while(valid_password == false);
		
		// Create new User
		User user = new User(App.getUsers().size(), name, email, phone, password, Role.Staff, 0);
		
		// Store ticket in Global Tickets List
		App.storeNewUser(user);
		
		System.out.println("An account for "+ user.getName() + " has now been created.");
		
		return;
	}
	
}
