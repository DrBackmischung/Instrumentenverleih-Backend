package de.wi2020sebgroup1.instrumentenverleih.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import de.wi2020sebgroup1.instrumentenverleih.entities.Ticket;
import de.wi2020sebgroup1.instrumentenverleih.entities.User;

public interface TicketRepository extends CrudRepository<Ticket, UUID> {
	
	Optional<List<Ticket>> findAllByUser(User user);
	
	List<Ticket> findAllByBookingID(UUID bookingID);

}
