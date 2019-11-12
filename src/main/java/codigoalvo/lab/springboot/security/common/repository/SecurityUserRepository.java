package codigoalvo.lab.springboot.security.common.repository;

import codigoalvo.lab.springboot.security.common.model.SecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecurityUserRepository extends JpaRepository<SecurityUser, Long> {

	Optional<SecurityUser> findFirstByLogin(String login);

}
