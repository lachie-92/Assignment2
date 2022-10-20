package Seeders;

import Models.App;
import Models.Role;
import Models.User;

public class UserSeeder {
	
	public static void seed_users(App App) {
		
		String default_password = "Aaaaaaaaaaa01111111111";

		// Initialise Level 1 Service Technicians
		App.storeNewUser(new User(App.getUsers().size(), "Harry Styles", "harry@cinco.com.au", "0400 001 002",
				default_password, Role.Technician, 1));
		App.storeNewUser(new User(App.getUsers().size(), "Niall Horan", "niall@cinco.com.au", "0400 002 002",
				default_password, Role.Technician, 1));
		App.storeNewUser(new User(App.getUsers().size(), "Liam Payne", "liam@cinco.com.au", "0400 003 003", default_password,
				Role.Technician, 1));

		// Initialise Level 2 Service Technicians
		App.storeNewUser(new User(App.getUsers().size(), "Louis Tomlinson", "louis@cinco.com.au", "0400 004 004",
				default_password, Role.Technician, 2));
		App.storeNewUser(new User(App.getUsers().size(), "Zayn Malik", "zayn@cinco.com.au", "0400 005 005", default_password,
				Role.Technician, 2));

		// Initialise a staff member
		App.storeNewUser(new User(App.getUsers().size(), "Chris Warrens", "chris@cinco.com.au", "0400 111 111",
				default_password, Role.Staff, 0));
		
		// Initialise a System Owner
		App.storeNewUser(new User(App.getUsers().size(), "Patricia Walker", "patricia@cinco.com.au", "0400 333 333",
				default_password, Role.SystemOwner, 0));
		
	}

}
