package codigoalvo.lab.springboot.security.common.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "security_authority")
public class SecurityAuthority {

	private Long id;

	private String name;

	public SecurityAuthority() {
	}

	public SecurityAuthority(String name) {
		this.setName(name);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Size(min = 2, max = 100)
	@Column(nullable = false, unique = true)
	public String getName() {
		return this.name == null ? null : this.name.toUpperCase();
	}

	public void setName(String name) {
		this.name = name == null ? null : name.toUpperCase();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SecurityAuthority that = (SecurityAuthority) o;
		return Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
