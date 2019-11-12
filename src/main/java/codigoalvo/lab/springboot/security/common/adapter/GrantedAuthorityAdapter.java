package codigoalvo.lab.springboot.security.common.adapter;

import codigoalvo.lab.springboot.security.common.model.SecurityAuthority;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

public class GrantedAuthorityAdapter implements GrantedAuthority {

	private SecurityAuthority securityAuthority;

	public GrantedAuthorityAdapter(SecurityAuthority securityAuthority) {
		this.securityAuthority = securityAuthority;
	}

	@Override
	public String getAuthority() {
		return this.securityAuthority == null ? "" : this.securityAuthority.getName();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GrantedAuthorityAdapter that = (GrantedAuthorityAdapter) o;
		return Objects.equals(this.getAuthority(), that.getAuthority());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getAuthority());
	}

}
