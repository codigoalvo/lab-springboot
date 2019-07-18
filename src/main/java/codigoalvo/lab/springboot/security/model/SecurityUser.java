package codigoalvo.lab.springboot.security.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "security_user")
public class SecurityUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	@Size(min = 5, max = 250)
	private String login;

	@NotNull
	@Size(min = 5, max = 250)
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "security_user_profile"
			, joinColumns = @JoinColumn(name = "id_user")
			, inverseJoinColumns = @JoinColumn(name = "id_profile")
			, foreignKey = @ForeignKey(name = "fk_user_sup")
			, inverseForeignKey = @ForeignKey(name = "fk_profile_sup")
			, uniqueConstraints = @UniqueConstraint(name = "pk_user_profile", columnNames = {"id_user", "id_profile"})
	)
	private List<SecurityProfile> profiles;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SecurityUser that = (SecurityUser) o;
		return Objects.equals(login, that.login);
	}

	@Override
	public int hashCode() {
		return Objects.hash(login);
	}

	/*
	@Override
	public String toString() {
		return login;
	}
	 */

}
