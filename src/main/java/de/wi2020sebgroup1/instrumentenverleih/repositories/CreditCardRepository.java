package de.wi2020sebgroup1.instrumentenverleih.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import de.wi2020sebgroup1.instrumentenverleih.entities.CreditCard;

public interface CreditCardRepository extends CrudRepository<CreditCard, UUID> {

}
