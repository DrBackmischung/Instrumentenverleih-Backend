package de.wi2020sebgroup1.instrumentenverleih.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import de.wi2020sebgroup1.instrumentenverleih.entities.Booking;
import de.wi2020sebgroup1.instrumentenverleih.entities.User;


public interface BookingRepositroy extends CrudRepository<Booking, UUID> {
	
	Optional<List<Booking>> findAllByUser(User user);

}
