package app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	@GetMapping(path="/konten", produces=MediaType.APPLICATION_JSON_VALUE)
	public CollectionModel<EntityModel<Konto>> getKonten() {
		List<EntityModel<Konto>> konten =repo.findAll().stream()
										.map(konto -> EntityModel.of(konto,
									WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MyController.class).getKonto(konto.getKontonummer())).withSelfRel()))
										.collect(Collectors.toList());
		
		return CollectionModel.of(konten);
	}
	
	@GetMapping(path="/konten/xml", produces=MediaType.APPLICATION_XML_VALUE)
	public List<Konto> getKontenasXML() {
		return repo.findAll();
	}
	
	//URL: /konten/100
	@GetMapping(path="/konten/{id}", produces=MediaType.APPLICATION_JSON_VALUE )
	public EntityModel<Konto> getKonto(@PathVariable Integer id) {
		 Konto konto = repo.findById(id).orElseThrow(() -> new KarrersException("gibts nicht") );
		
		EntityModel<Konto> kontomodel = EntityModel.of(
				konto,
				WebMvcLinkBuilder.linkTo(MyController.class).slash("konten").slash(id.toString()).withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MyController.class).getKonto(id)).withRel("weiteres self"),
				WebMvcLinkBuilder.linkTo(MyController.class).slash("konten").withRel("all"));
		
		return kontomodel;
		
	}
	
//	@GetMapping(path="/konten/xml/{id}", produces=MediaType.APPLICATION_XML_VALUE)
//	public Konto getKontoasXML(@PathVariable Integer id) {
//		return getKonto(id);
//	}
	
	@PostMapping(path = "/konten/xml", consumes = MediaType.APPLICATION_XML_VALUE, produces=MediaType.APPLICATION_XML_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED )
	public Konto createKontoasXML(@RequestBody Konto k) {
		if (repo.existsById(k.getKontonummer())) {
			throw new KarrersException("war update statt create");
		}
		return repo.save(k);
	}
	
	@PostMapping(path = "/konten", consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED )
	public Konto createKonto(@RequestBody Konto k) {
		if (repo.existsById(k.getKontonummer())) {
			throw new KarrersException("war update statt create");
		}
		return repo.save(k);
	}
	
	@PutMapping("/konten/{id}")
	public Konto updateKonto(@RequestBody Konto k, @PathVariable Integer id) {
		Optional<Konto> optkonto = repo.findById(id);
		if (optkonto.isPresent()) {
			repo.save(k); return k;
		}
		else {
			throw new KarrersException("war create statt update");
		}
	}
	
	@DeleteMapping("/konten/{id}")
	public void deleteKonto( @PathVariable Integer id) {
		repo.deleteById(id);
	}
	
	
	@ExceptionHandler(KarrersException.class)
	public ResponseEntity<Error> getError(KarrersException ex){
		return new ResponseEntity<Error>(new Error("Karrers Fehler", ex), HttpStatus.BAD_REQUEST);
	}

}
