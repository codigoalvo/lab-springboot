package codigoalvo.lab.springboot.security.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "security_profile")
public class SecurityProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "security_profile_authority"
			, joinColumns = @JoinColumn(name = "id_profile")
			, inverseJoinColumns = @JoinColumn(name = "id_authority")
			, foreignKey = @ForeignKey(name = "fk_profile_pa")
			, inverseForeignKey = @ForeignKey(name = "fk_authority_pa")
			, uniqueConstraints = @UniqueConstraint(name = "pk_profile_authority", columnNames = {"id_profile", "id_authority"})
	)
	private List<SecurityAuthority> authorities;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SecurityProfile securityProfile = (SecurityProfile) o;
		return Objects.equals(id, securityProfile.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
