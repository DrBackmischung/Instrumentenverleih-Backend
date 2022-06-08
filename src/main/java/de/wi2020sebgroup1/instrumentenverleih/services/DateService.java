package de.wi2020sebgroup1.instrumentenverleih.services;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class DateService {
	
	public LocalDate getDate(){
		
		LocalDate date = LocalDate.now();
		return date;
		
	}

}
