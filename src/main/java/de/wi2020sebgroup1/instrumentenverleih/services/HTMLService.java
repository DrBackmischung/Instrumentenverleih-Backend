package de.wi2020sebgroup1.instrumentenverleih.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.wi2020sebgroup1.instrumentenverleih.configurationObject.EmailVariablesObject;

@Service
public class HTMLService {
	
	@Autowired
	DateService dateService;
	
	public String read(String fileName, EmailVariablesObject evo) {
		
		String s = null;
		URL url = null;
		BufferedReader br = null;
		
		try {

			url = new URL("https://raw.githubusercontent.com/DrBackmischung/Kino-Email/main/html/"+fileName);
			br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line = br.readLine();
            StringBuilder sb = new StringBuilder();

            while (line != null) {
                sb.append(line).append("\n");
                line = br.readLine();
            }

            s = sb.toString();
			br.close();
			
        } catch (Exception e) {
			e.printStackTrace();
		}
		
		s = s.replace("USERNAME", evo.getUsername());
		s = s.replace("FIRSTNAME", evo.getFirstName());
		s = s.replace("LASTNAME", evo.getLastName());
		s = s.replace("FILE", evo.getFile());
		s = s.replace("LINK", evo.getLink());
		s = s.replace("BOOKINGDATE", evo.getBookingDate());
		s = s.replace("CATEGORY", evo.getCategory());
		s = s.replace("INSTRUMENT", evo.getInstrument());
		s = s.replace("I-S1", evo.getString1());
		s = s.replace("I-S2", evo.getString2());
		s = s.replace("I-S3", evo.getString3());
		
		return s;
	}
	
}
