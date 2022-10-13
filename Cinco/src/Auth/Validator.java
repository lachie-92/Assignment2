package Auth;

import java.util.regex.Pattern;

public class Validator {
	
	/* Validate user input meets email requirements */
	public static Boolean validateEmail(String email) {
		// Email regex RFC 2822 Standard email validation from https://www.w3resource.com/javascript/form/email-validation.php
		String email_regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		
		// Validate that the user name is a valid email address.
		if(Pattern.matches(email_regex, email)) {		
			return true;
		}
		System.out.println("This is not a valid email address.");
		return false;
	}
	
	/* Validate user input meets password requirements */
	public static Boolean validatePassword(String password) {
		// Validatate
		String password_regex = "^[a-zA-Z0-9]{20,}$";
		// Validate that the password meets system requirements.
		if(Pattern.matches(password_regex, password)) {
			return true;
		}
		System.out.println("This is not a valid password.");
		return false;
	}

}
