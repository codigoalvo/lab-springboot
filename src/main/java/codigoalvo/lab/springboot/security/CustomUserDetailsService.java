package codigoalvo.lab.springboot.security;

import codigoalvo.lab.springboot.model.Usuario;
import codigoalvo.lab.springboot.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOpt = usuarioRepository.findFirstByEmail(userEmail);
		Usuario usuario = usuarioOpt.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha inválido(s)"));
		log.debug("************* Usuario: " + usuario + "UserDetailsAdapter: " + usuario);
		UserDetailsAdapter response = new UserDetailsAdapter(usuarioOpt.get());
		return response;
	}

}
