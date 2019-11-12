package codigoalvo.lab.springboot.entities.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Data
@Entity
@Table(name = "categoria")
public class Categoria implements Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 3, max = 20)
	private String nome;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Categoria categoria = (Categoria) o;
		return getId().equals(categoria.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

}
