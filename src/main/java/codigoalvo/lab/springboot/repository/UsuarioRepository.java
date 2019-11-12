package codigoalvo.lab.springboot.repository;

import codigoalvo.lab.springboot.entities.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
