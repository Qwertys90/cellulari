package it.dstech.controller;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.dstech.models.Cellulare;
import it.dstech.models.User;
import it.dstech.services.CellulareService;
import it.dstech.services.UserService;


@RestController
@RequestMapping("/cellulare")
public class CellulareController {
	
	@Autowired
	private CellulareService cellulareService;
	
	@Autowired
	private UserService userService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/getmodel")
	public ResponseEntity<Cellulare> getmodel() {
		Cellulare prodott = new Cellulare();
		return new ResponseEntity<Cellulare>(prodott, HttpStatus.CREATED);
	}

	
	@PostMapping("/saveupdate")
	public ResponseEntity<Cellulare> saveOrUpdateCellulare(@RequestBody Cellulare cellulare) {
		try {
			Cellulare saved = cellulareService.saveOrUpdateCellulare(cellulare);
			logger.info(saved + " saved");
			return new ResponseEntity<Cellulare>(saved, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Errore " + e);
			return new ResponseEntity<Cellulare>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Cellulare> deleteCellulare(@PathVariable("id") int id) {
		try {
			cellulareService.deleteCellulare(id);
			logger.info(id + " deleted");
			return new ResponseEntity<Cellulare>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Errore " + e);
			return new ResponseEntity<Cellulare>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<Cellulare>> getAll() {
		try {
			List<Cellulare> listaCellulari = cellulareService.findAll();
			return new ResponseEntity<List<Cellulare>>(listaCellulari, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Errore " + e);
			return new ResponseEntity<List<Cellulare>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/addcellulare/{cellulareid}")
	public ResponseEntity<User> addCellulare(@PathVariable("cellulareid") int idCell) {
		try {
			Cellulare cellulare = cellulareService.findById(idCell);
			if(cellulare.getQuantità()>0) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());			
			user.getListaCellulari().add(cellulareService.findById(idCell));
			userService.saveUser(user);
			cellulare.setQuantità(cellulare.getQuantità()-1);
			cellulareService.saveOrUpdateCellulare(cellulare);
			return new ResponseEntity<User>(HttpStatus.OK);
			}else {
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			logger.error("Errore " + e);
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/resituiscicellulare/{cellulareid}")
	public ResponseEntity<User> resistuisciCellulare(@PathVariable("cellulareid") int idCell) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());	
			Cellulare cellulare = cellulareService.findById(idCell);
			List<Cellulare> listaCellulari = cellulareService.findByUserId(user.getId());
			boolean posseduto = false;
			for(Cellulare cell : listaCellulari) {
				if(cell.getId()==cellulare.getId())
					posseduto=true;
			}
			if(posseduto) {
					
			user.getListaCellulari().remove(cellulareService.findById(idCell));
			userService.saveUser(user);
			cellulare.setQuantità(cellulare.getQuantità()+1);
			cellulareService.saveOrUpdateCellulare(cellulare);
			return new ResponseEntity<User>(HttpStatus.OK);
			}else {
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			logger.error("Errore " + e);
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/cellulariacquistati")
	public ResponseEntity<List<Cellulare>> getAllAcquistati() {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			int userid = userService.findByUsername(auth.getName()).getId();	
			List<Cellulare> listaCellulari = cellulareService.findByUserId(userid);
			return new ResponseEntity<List<Cellulare>>(listaCellulari, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Errore " + e);
			return new ResponseEntity<List<Cellulare>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
