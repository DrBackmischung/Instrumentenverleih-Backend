package de.wi2020sebgroup1.instrumentenverleih.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import de.wi2020sebgroup1.instrumentenverleih.entities.CinemaRoom;
import de.wi2020sebgroup1.instrumentenverleih.entities.CinemaRoomSeatingPlan;

public interface CinemaRoomSeatingPlanRepository extends CrudRepository<CinemaRoomSeatingPlan, UUID> {
	
	Optional<CinemaRoomSeatingPlan> findByCinemaRoom(CinemaRoom cinemaRoom);
	
}
