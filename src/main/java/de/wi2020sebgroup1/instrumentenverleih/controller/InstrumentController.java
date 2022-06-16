package de.wi2020sebgroup1.instrumentenverleih.controller;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.wi2020sebgroup1.instrumentenverleih.entities.Instrument;
import de.wi2020sebgroup1.instrumentenverleih.exceptions.InstrumentNotFoundException;
import de.wi2020sebgroup1.instrumentenverleih.repositories.InstrumentRepository;

@Controller
@RestController
@RequestMapping("/instrument")
public class InstrumentController {
	
	@Autowired
	InstrumentRepository instrumentRepository;
	
	@PutMapping("/add")
	public ResponseEntity<Object> add(@RequestBody Instrument i) {
		
		try {
			return new ResponseEntity<Object>(instrumentRepository.save(i), HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.CONFLICT);
		}
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<Object> getAll(){
		return new ResponseEntity<Object>(instrumentRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getSpecific(@PathVariable UUID id){
		try {
			return new ResponseEntity<Object>(instrumentRepository.findById(id).get(), HttpStatus.OK);
		}
		catch(NoSuchElementException e) {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable UUID id){
		
		try {
			Optional<Instrument> o = instrumentRepository.findById(id);
			instrumentRepository.deleteById(o.get().getId());
			return new ResponseEntity<>(id, HttpStatus.OK);
		} catch (NoSuchElementException nSE) {
			return new ResponseEntity<Object>(new InstrumentNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
