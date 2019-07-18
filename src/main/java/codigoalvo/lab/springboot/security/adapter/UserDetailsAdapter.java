package codigoalvo.lab.springboot.security.adapter;

import codigoalvo.lab.springboot.security.model.SecurityUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class UserDetailsAdapter implements UserDetails {

	private SecurityUser securityUser;

	public UserDetailsAdapter(SecurityUser securityUser) {
		this.securityUser = securityUser == null ? new SecurityUser() : securityUser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		final List<GrantedAuthorityAdapter> authorities = new ArrayList<>();
		securityUser.getProfiles().forEach(p -> p.getAuthorities().forEach(a -> authorities.add(new GrantedAuthorityAdapter(a))));
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.securityUser == null ? null : securityUser.getPassword();
	}

	@Override
	public String getUsername() {
		return this.securityUser == null ? null : securityUser.getLogin();
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
		return this.securityUser != null;
	}

}
