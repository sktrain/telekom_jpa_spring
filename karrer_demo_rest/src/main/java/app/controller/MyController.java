package app.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.model.Konto;
import app.repo.KontoRepository;

@RestController()
public class MyController {
	
	@Autowired
	private KontoRepository repo;
	
	
	public MyController() {
	}
	
	@PostConstruct
	public void init() {
		for (int i = 1; i <=3; ++i) {
			repo.save(new Konto(i, 1000 + i));
		}
	}
	
	@GetMapping("/konten")
	public List<Konto> getKonten() {
		return repo.findAll();
	}
	
	//URL: /konten/100
	@GetMapping("/konten/{id}")
	public Konto getKonto(@PathVariable Integer id) {
		Optional<Konto> optkonto = repo.findById(id);
		return optkonto.get();
	}
	
	@PostMapping("/konten")
	public Konto createKonto(@RequestBody Konto k) {
		return repo.save(k);
	}
	
	@PutMapping("/konten/{id}")
	public Konto updateKonto(@RequestBody Konto k, @PathVariable Integer id) {
		Optional<Konto> optkonto = repo.findById(id);
		if (optkonto.isPresent()) {
			repo.save(k);
		}
		return k;
	}
	
	@DeleteMapping("/konten/{id}")
	public void deleteKonto( @PathVariable Integer id) {
		repo.deleteById(id);
	}
	
	

}
