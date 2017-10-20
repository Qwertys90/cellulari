package it.dstech.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.dstech.models.Cellulare;
import it.dstech.repository.CellulareRepository;

@Service
public class CellulareServiceImpl implements CellulareService {

	@Autowired
	CellulareRepository cellulareRepository;
	
	@Override
	public Cellulare saveOrUpdateCellulare(Cellulare cellulare) {
		return cellulareRepository.save(cellulare);
	}

	@Override
	public List<Cellulare> findAll() {
		return (List<Cellulare>) cellulareRepository.findAll();
	}

	@Override
	public void deleteCellulare(int id) {
		cellulareRepository.delete(id);
	}
	@Override
	public Cellulare findById(int id) {
		return cellulareRepository.findOne(id);
	}
	@Override
	public List<Cellulare> findByUserId(int id) {
		return (List<Cellulare>) cellulareRepository.findByUser_id(id);
	}

}
