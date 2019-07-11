package codigoalvo.lab.springboot.security;

import codigoalvo.lab.springboot.model.Usuario;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
public class UserDetailsAdapter implements UserDetails {

	private Usuario usuario;

	public UserDetailsAdapter(Usuario usuario) {
		this.usuario = usuario == null ? new Usuario() : usuario;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (usuario.getPerfil() == null || usuario.getPerfil().getPermissoes() == null) {
			return null;
		}
		List<GrantedAuthorityAdapter> authorities = new ArrayList<>();
		usuario.getPerfil().getPermissoes().forEach(p -> authorities.add(new GrantedAuthorityAdapter(p)));
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.usuario == null ? null : usuario.getSenha();
	}

	@Override
	public String getUsername() {
		return this.usuario == null ? null : usuario.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return isValid();
	}

	@Override
	public boolean isAccountNonLocked() {
		return isValid();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isValid();
	}

	@Override
	public boolean isEnabled() {
		return isValid();
	}

	private boolean isValid() {
		return this.usuario == null ? false : true;
	}

}
