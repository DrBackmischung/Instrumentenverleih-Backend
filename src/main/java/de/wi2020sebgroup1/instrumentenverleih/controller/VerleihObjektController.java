package de.wi2020sebgroup1.instrumentenverleih.controller;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

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

import de.wi2020sebgroup1.instrumentenverleih.entities.VerleihObjekt;
import de.wi2020sebgroup1.instrumentenverleih.exceptions.VerleihObjektNotFoundException;
import de.wi2020sebgroup1.instrumentenverleih.repositories.VerleihObjektRepository;

@Controller
@RestController
@RequestMapping("/vo")
public class VerleihObjektController {
	
	@Autowired
	VerleihObjektRepository repo;
	
	@PutMapping("/{id}/add")
	public ResponseEntity<Object> add1(@PathVariable UUID id){
		
		try {
			VerleihObjekt vo = repo.findById(id).get();
			vo.setAmount(vo.getAmount() + 1);
			return new ResponseEntity<>(repo.save(vo), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(new VerleihObjektNotFoundException(id).getMessage(), HttpStatus.OK);
		}
		
	}
	
	@PutMapping("/add")
	public ResponseEntity<Object> addBooking(@RequestBody VerleihObjekt vo){
		
		return new ResponseEntity<>(repo.save(vo), HttpStatus.OK);
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<Object> getAll(){
		return new ResponseEntity<Object>(repo.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getSpecific(@PathVariable UUID id){
		try {
			return new ResponseEntity<Object>(repo.findById(id).get(), HttpStatus.OK);
		}
		catch(NoSuchElementException e) {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable UUID id){
		
		try {
			Optional<VerleihObjekt> o = repo.findById(id);
			repo.deleteById(o.get().getId());
			return new ResponseEntity<>(id, HttpStatus.OK);
		} catch (NoSuchElementException nSE) {
			return new ResponseEntity<Object>(new VerleihObjektNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
