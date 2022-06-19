package de.wi2020sebgroup1.instrumentenverleih.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import de.wi2020sebgroup1.instrumentenverleih.entities.MarktplatzInstrument;
import de.wi2020sebgroup1.instrumentenverleih.entities.ServicePortal;


public interface ServiceRepository extends CrudRepository<ServicePortal, UUID> {

}
