package codigoalvo.lab.springboot.security;

import codigoalvo.lab.springboot.model.Usuario;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Data
public class CustomUserDetails implements UserDetails {

	private static final String ROLES = "ADMIN;USER";

	private Usuario usuario;

	public CustomUserDetails(Usuario usuario) {
		this.usuario = usuario == null ? new Usuario() : usuario;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> roles = new ArrayList<>();
		Arrays.asList(ROLES.split(";")).forEach(e -> roles.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return e;
			}
		}));
		return roles;
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
