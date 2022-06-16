package de.wi2020sebgroup1.instrumentenverleih.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.wi2020sebgroup1.instrumentenverleih.entities.InstrumentDetailsText;
import de.wi2020sebgroup1.instrumentenverleih.repositories.InstrumentDetailsTextRepository;

@Controller
@RestController
@RequestMapping("/instrumentdetailstext")
public class InstrumentDetailsTextController {
	
	@Autowired
	InstrumentDetailsTextRepository idtRepository;
	
	@PutMapping("/add")
	public ResponseEntity<Object> add(@RequestBody InstrumentDetailsText i) {
		
		i.setId(UUID.randomUUID());
		return new ResponseEntity<Object>(idtRepository.save(i), HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<Object> getAll(){
		return new ResponseEntity<Object>(idtRepository.findAll(), HttpStatus.OK);
	}

}
