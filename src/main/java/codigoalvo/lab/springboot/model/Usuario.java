package codigoalvo.lab.springboot.model;

import codigoalvo.lab.springboot.type.TipoPerfil;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	@Size(min = 5, max = 250)
	private String email;

	@NotNull
	@Size(min = 2, max = 200)
	private String nome;

	@NotNull
	@Size(min = 3, max = 100)
	private String senha;

	@NotNull
	private TipoPerfil perfil;

}
