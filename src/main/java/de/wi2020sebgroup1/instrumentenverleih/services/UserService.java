package de.wi2020sebgroup1.instrumentenverleih.services;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.wi2020sebgroup1.instrumentenverleih.entities.User;
import de.wi2020sebgroup1.instrumentenverleih.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User resolve(UUID userID) {
		
		Optional<User> user = userRepository.findById(userID);
		
		try {
			return user.get();
		} catch(NoSuchElementException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
