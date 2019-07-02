package codigoalvo.lab.springboot.security;

import codigoalvo.lab.springboot.model.Usuario;
import codigoalvo.lab.springboot.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findFirstByEmail(userEmail);
		CustomUserDetails response = new CustomUserDetails(usuario);
		log.debug("************* Usuario: "+usuario + "CustomUserDetails: "+response);
		return response;
	}

}
