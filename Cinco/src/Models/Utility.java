package Models;

import java.time.Duration;
import java.time.LocalDateTime;

public class Utility {
	
	public static void print_error(String error) {
		System.out.println("------------------------------------------------------");
		System.out.println(error);
		System.out.println("------------------------------------------------------");	
	}
	
	public static void print_hr(int characters) {
		String hr = "-".repeat(characters);
		System.out.println(hr);
	}
	
	public static String time_between_dates(LocalDateTime date_1, LocalDateTime date_2) {
		
		Duration duration = Duration.between(date_1, date_2);
		
		// Taken from https://stackoverflow.com/questions/3471397/how-can-i-pretty-print-a-duration-in-java
		return duration.toString().substring(2).replaceAll("(\\d[HMS])(?!$)", "$1 ").toLowerCase();
		
	}

}
