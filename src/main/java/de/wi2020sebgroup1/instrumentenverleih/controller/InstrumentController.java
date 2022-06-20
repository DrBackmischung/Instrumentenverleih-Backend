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
import de.wi2020sebgroup1.instrumentenverleih.repositories.InstrumentDetailsTextRepository;
import de.wi2020sebgroup1.instrumentenverleih.repositories.InstrumentHighlightTextRepository;
import de.wi2020sebgroup1.instrumentenverleih.repositories.InstrumentRepository;

@Controller
@RestController
@RequestMapping("/instrument")
public class InstrumentController {
	
	@Autowired
	InstrumentRepository instrumentRepository;
	
	@Autowired
	InstrumentDetailsTextRepository instrumentDetailsTextRepository;
	
	@Autowired
	InstrumentHighlightTextRepository instrumentHighlightTextRepository;
	
	@PutMapping("/add")
	public ResponseEntity<Object> add(@RequestBody Instrument ico) {
		
		
		
		/*for(UUID u : ico.detailSections) {
			i.addIDT(instrumentDetailsTextRepository.findById(u).get());
		}
		
		for(UUID u : ico.highlightList) {
			i.addIHT(instrumentHighlightTextRepository.findById(u).get());
		}
		
		for(InstrumentDetailsText o : i.getDetailSections()) {
			instrumentDetailsTextRepository.save(o);
		}
		
		for(InstrumentHighlightText o : i.getHighlightList()) {
			instrumentHighlightTextRepository.save(o);
		}*/
		
		return new ResponseEntity<Object>(instrumentRepository.save(ico), HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<Object> getAll(){
		Iterable<Instrument> resultSet = instrumentRepository.findAll();
		return new ResponseEntity<Object>(resultSet, HttpStatus.OK);
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
	
	@PutMapping("/{id}/add")
	public ResponseEntity<Object> add1(@PathVariable UUID id){
		
		try {
			Instrument vo = instrumentRepository.findById(id).get();
			vo.setAmount(vo.getAmount() + 1);
			return new ResponseEntity<>(instrumentRepository.save(vo), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(new InstrumentNotFoundException(id).getMessage(), HttpStatus.OK);
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
