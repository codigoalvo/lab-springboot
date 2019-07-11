package codigoalvo.lab.springboot.security;

import codigoalvo.lab.springboot.model.Perfil;
import codigoalvo.lab.springboot.model.Permissao;
import codigoalvo.lab.springboot.type.TipoPerfil;
import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityAdapter implements GrantedAuthority {

	private Permissao permissao;

	public GrantedAuthorityAdapter(Permissao permissao) {
		this.permissao = permissao;
	}

	@Override
	public String getAuthority() {
		return this.permissao == null ? "" : this.permissao.getPermissao();
	}

}
