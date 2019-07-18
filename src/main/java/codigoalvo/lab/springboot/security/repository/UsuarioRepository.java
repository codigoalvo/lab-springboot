package codigoalvo.lab.springboot.security.repository;

import codigoalvo.lab.springboot.security.model.SecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<SecurityUser, Long> {

	Optional<SecurityUser> findFirstByLogin(String login);

}
