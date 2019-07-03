package codigoalvo.lab.springboot.security;

import codigoalvo.lab.springboot.type.TipoPerfil;
import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityAdapter implements GrantedAuthority {

	private TipoPerfil perfil;

	public GrantedAuthorityAdapter(TipoPerfil perfil) {
		this.perfil = perfil;
	}

	@Override
	public String getAuthority() {
		return this.perfil == null ? "" : this.perfil.getKey();
	}

}
