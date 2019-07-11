package codigoalvo.lab.springboot.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "permissao")
public class Permissao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 2, max = 100)
	private String permissao;

	public String getPermissao() {
		return this.permissao == null ? null : this.permissao.toUpperCase();
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao == null ? null : permissao.toUpperCase();
	}

}
