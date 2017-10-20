package it.dstech.services;

import java.util.List;

import it.dstech.models.Cellulare;

public interface CellulareService {

	Cellulare saveOrUpdateCellulare(Cellulare cellulare);
	
	List<Cellulare> findAll();
	
	void deleteCellulare(int id);
	
	public Cellulare findById(int id);
	
	public List<Cellulare> findByUserId(int id);
	
}
