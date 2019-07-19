package codigoalvo.lab.springboot.security.service;

import codigoalvo.lab.springboot.security.adapter.UserDetailsAdapter;
import codigoalvo.lab.springboot.security.model.SecurityUser;
import codigoalvo.lab.springboot.security.repository.SecurityUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private SecurityUserRepository securityUserRepository;

	@Override
	public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {
		Optional<SecurityUser> usuarioOpt = securityUserRepository.findFirstByEmail(userLogin);
		SecurityUser securityUser = usuarioOpt.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou password inválido(s)"));
		log.debug("************* Usuario: " + securityUser + "UserDetailsAdapter.securityUser: " + securityUser);
		UserDetailsAdapter response = new UserDetailsAdapter(usuarioOpt.get());
		return response;
	}

	public UserDetailsAdapter getAuthenticated() {
		try {
			final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			log.debug("CustomUserDetailsService.getAuthenticated() - " + authentication);
			if (authentication != null) {
				final Object principal = authentication.getPrincipal();
				log.debug("CustomUserDetailsService.getAuthenticated().getPrincipal() - " + principal);
				if (principal != null) {
					if (principal instanceof UserDetailsAdapter) {
						return (UserDetailsAdapter) principal;
					}
					String userName = Objects.toString(principal);
					return new UserDetailsAdapter(securityUserRepository.findFirstByEmail(userName).get());
				}
			}
		} catch (final Throwable ex) {
			log.error("CustomUserDetailsService.getAuthenticated() - Erro ao buscar detalhes de usuário autenticado", ex);
		}
		return null;
	}

}
