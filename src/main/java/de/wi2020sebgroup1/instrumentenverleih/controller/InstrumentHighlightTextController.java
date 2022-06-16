package de.wi2020sebgroup1.instrumentenverleih.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.wi2020sebgroup1.instrumentenverleih.entities.InstrumentHighlightText;
import de.wi2020sebgroup1.instrumentenverleih.repositories.InstrumentHighlightTextRepository;

@Controller
@RestController
@RequestMapping("/instrumenthighlighttext")
public class InstrumentHighlightTextController {
	
	@Autowired
	InstrumentHighlightTextRepository ihtRepository;
	
	@PutMapping("/add")
	public ResponseEntity<Object> add(@RequestBody InstrumentHighlightText i) {
		
		return new ResponseEntity<Object>(ihtRepository.save(i), HttpStatus.CREATED);
		
	}

}
