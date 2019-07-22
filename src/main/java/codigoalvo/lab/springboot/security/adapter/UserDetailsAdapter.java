package codigoalvo.lab.springboot.security.adapter;

import codigoalvo.lab.springboot.security.model.SecurityUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDetailsAdapter implements UserDetails {

	@JsonIgnore
	private SecurityUser securityUser = new SecurityUser();

	public UserDetailsAdapter(SecurityUser securityUser) {
		if (securityUser != null) {
			BeanUtils.copyProperties(securityUser, this.securityUser, "loggedUser");
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		final Set<GrantedAuthorityAdapter> authorities = new HashSet<>();
		securityUser.getProfiles().forEach(p -> p.getAuthorities().forEach(a -> authorities.add(new GrantedAuthorityAdapter(a))));
		return Collections.unmodifiableCollection(authorities);
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return this.securityUser == null ? null : securityUser.getPassword();
	}

	@Override
	public String getUsername() {
		return this.securityUser == null ? null : securityUser.getLogin();
	}

	public String getName() {
		return this.securityUser == null ? null : securityUser.getName();
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
