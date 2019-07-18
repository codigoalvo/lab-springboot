package codigoalvo.lab.springboot.security.service;

import codigoalvo.lab.springboot.security.adapter.UserDetailsAdapter;
import codigoalvo.lab.springboot.security.model.SecurityUser;
import codigoalvo.lab.springboot.security.repository.UsuarioRepository;
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
	public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {
		Optional<SecurityUser> usuarioOpt = usuarioRepository.findFirstByLogin(userLogin);
		SecurityUser securityUser = usuarioOpt.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou password inválido(s)"));
		log.debug("************* Usuario: " + securityUser + "UserDetailsAdapter.securityUser: " + securityUser);
		UserDetailsAdapter response = new UserDetailsAdapter(usuarioOpt.get());
		return response;
	}

}
