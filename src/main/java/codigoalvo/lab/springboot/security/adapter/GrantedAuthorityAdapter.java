package codigoalvo.lab.springboot.security.adapter;

import codigoalvo.lab.springboot.security.model.SecurityAuthority;
import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityAdapter implements GrantedAuthority {

	private SecurityAuthority securityAuthority;

	public GrantedAuthorityAdapter(SecurityAuthority securityAuthority) {
		this.securityAuthority = securityAuthority;
	}

	@Override
	public String getAuthority() {
		return this.securityAuthority == null ? "" : this.securityAuthority.getName();
	}

}
