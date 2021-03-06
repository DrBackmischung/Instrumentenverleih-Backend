package de.wi2020sebgroup1.instrumentenverleih.controller;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.wi2020sebgroup1.instrumentenverleih.configurationObject.EmailVariablesObject;
import de.wi2020sebgroup1.instrumentenverleih.configurationObject.UserLoginObject;
import de.wi2020sebgroup1.instrumentenverleih.configurationObject.UserRegistrationObject;
import de.wi2020sebgroup1.instrumentenverleih.entities.User;
import de.wi2020sebgroup1.instrumentenverleih.exceptions.UserAlreadyExistsException;
import de.wi2020sebgroup1.instrumentenverleih.repositories.UserRepository;
import de.wi2020sebgroup1.instrumentenverleih.services.EmailService;

@Controller
@RestController
public class AccountController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EmailService emailService;
	
	@PutMapping("/registration")
	public ResponseEntity<Object> register(@RequestBody UserRegistrationObject uro){
		
		boolean alreadyExists = false;
		
		if(!(uro.passwordHash.equals(uro.passwordConfirmHash))) {
			return new ResponseEntity<Object>("Incorrect password!", HttpStatus.UNAUTHORIZED);
		}
		
		User user = userRepository.findByUsernameSpecial(uro.username);
		if(user != null) {
			alreadyExists = true;
		}
		
		try {
			user = userRepository.findByEmailEquals(uro.email).get();
			alreadyExists = true;
		}catch(Exception e) {};
		
		if(alreadyExists) {
			return new ResponseEntity<Object>(new UserAlreadyExistsException(uro.email, uro.username), HttpStatus.NOT_ACCEPTABLE);
		}
		
		User toAdd = new User();
		
		toAdd.setUserName(uro.username);
		toAdd.setEmail(uro.email);
		toAdd.setName(uro.name);
		toAdd.setFirstName(uro.firstName);
		toAdd.setPassword(uro.passwordHash);
		toAdd.setStreet(uro.street);
		toAdd.setNumber(uro.number);
		toAdd.setCity(uro.city);

		emailService.sendMail(uro.email, "Registration completed!", new EmailVariablesObject(uro.username, uro.firstName, uro.name, "", "", "", "", "", "", "", ""), "Registration.html");
		
		return new ResponseEntity<Object>(userRepository.save(toAdd), HttpStatus.CREATED);
	}
	
	@PutMapping("/login")
	public ResponseEntity<Object> login(HttpServletResponse response, @RequestBody UserLoginObject ulo){
		
		Optional<User> userSearch = userRepository.findByUsername(ulo.username);
		User u = null;
		try {
			u = userSearch.get();
			if(!(u.getPassword().equals(ulo.passwordHash))) {
				return new ResponseEntity<Object>("Incorrect password!", HttpStatus.UNAUTHORIZED);
			}
		} catch(NoSuchElementException e) {
			return new ResponseEntity<Object>("No user for username found!", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Object>(u, HttpStatus.OK);
		
	}
	
}
