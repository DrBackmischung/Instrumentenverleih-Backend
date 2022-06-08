package de.wi2020sebgroup1.instrumentenverleih.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import de.wi2020sebgroup1.instrumentenverleih.entities.Seat;
import de.wi2020sebgroup1.instrumentenverleih.entities.Show;

public interface SeatRepository extends CrudRepository<Seat, UUID> {
	
	Optional<List<Seat>> findAllByShow(Show show);
	void deleteAllByShow(Show show);

}
	