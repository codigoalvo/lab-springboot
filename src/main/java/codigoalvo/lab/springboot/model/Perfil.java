package codigoalvo.lab.springboot.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "perfil")
public class Perfil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 2, max = 100)
	private String nome;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "perfil_permissao"
			, joinColumns = @JoinColumn(name = "id_perfil")
			, inverseJoinColumns = @JoinColumn(name = "id_permissao")
			, foreignKey = @ForeignKey(name = "fk_perfil_pp")
			, inverseForeignKey = @ForeignKey(name = "fk_permissao_pp")
			, uniqueConstraints = @UniqueConstraint(name = "pk_perfil_permissao", columnNames = {"id_perfil", "id_permissao"})
	)
	private List<Permissao> permissoes;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Perfil perfil = (Perfil) o;
		return Objects.equals(id, perfil.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
