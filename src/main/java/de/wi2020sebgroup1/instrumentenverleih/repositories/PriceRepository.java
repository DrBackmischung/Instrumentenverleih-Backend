package de.wi2020sebgroup1.instrumentenverleih.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import de.wi2020sebgroup1.instrumentenverleih.entities.Price;
import de.wi2020sebgroup1.instrumentenverleih.enums.SeatType;

public interface PriceRepository extends CrudRepository<Price, UUID> {
	
	public Optional<Price> findByType(SeatType type);

}
