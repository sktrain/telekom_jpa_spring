package app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import app.model.Konto;

public interface KontoRepository extends JpaRepository<Konto, Integer>{

}
