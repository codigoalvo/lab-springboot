package codigoalvo.lab.springboot.repository;

import codigoalvo.lab.springboot.entities.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
