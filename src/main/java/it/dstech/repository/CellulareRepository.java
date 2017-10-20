package it.dstech.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.dstech.models.Cellulare;

public interface CellulareRepository extends CrudRepository<Cellulare, Integer> {

	Cellulare findById(int id);
	
	List<Cellulare> findByUser_id(int id);
	
}
