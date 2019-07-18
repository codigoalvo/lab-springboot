package codigoalvo.lab.springboot.security.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "security_authority")
public class SecurityAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 2, max = 150)
	private String name;

	public String getName() {
		return this.name == null ? null : this.name.toUpperCase();
	}

	public void setName(String name) {
		this.name = name == null ? null : name.toUpperCase();
	}

}
