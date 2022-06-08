package de.wi2020sebgroup1.instrumentenverleih.controller;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.wi2020sebgroup1.instrumentenverleih.entities.User;
import de.wi2020sebgroup1.instrumentenverleih.exceptions.UserNotFoundException;
import de.wi2020sebgroup1.instrumentenverleih.repositories.BookingRepositroy;
import de.wi2020sebgroup1.instrumentenverleih.repositories.UserRepository;

@Controller
@RestController
@RequestMapping("/user")
public class ProfileController {
	
	@Autowired
	BookingRepositroy bookingRepositroy;
	
	@Autowired
	UserRepository userReporitory;
	
	@GetMapping("/{id}/bookings")
	public ResponseEntity<Object> getBookings(@PathVariable UUID id){
		Optional<User> u = userReporitory.findById(id);
		try {
			User user = u.get();
			return new ResponseEntity<Object>(bookingRepositroy.findAllByUser(user), HttpStatus.OK);
		} catch(NoSuchElementException e) {
			return new ResponseEntity<Object>(new UserNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
