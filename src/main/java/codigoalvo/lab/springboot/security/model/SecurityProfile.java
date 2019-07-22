package codigoalvo.lab.springboot.security.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "security_profile")
public class SecurityProfile {


	private Long id;

	private String name;

	private Set<SecurityAuthority> authorities;

	public SecurityProfile() {
	}

	public SecurityProfile(String name) {
		this.setName(name);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "security_profile_authority"
			, joinColumns = @JoinColumn(name = "id_profile")
			, inverseJoinColumns = @JoinColumn(name = "id_authority")
			, foreignKey = @ForeignKey(name = "fk_profile_pa")
			, inverseForeignKey = @ForeignKey(name = "fk_authority_pa")
			, uniqueConstraints = @UniqueConstraint(name = "pk_profile_authority", columnNames = {"id_profile", "id_authority"})
	)
	public Set<SecurityAuthority> getAuthorities() {
		return authorities;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAuthorities(Set<SecurityAuthority> authorities) {
		this.authorities = authorities;
	}

	public void addAuthority(SecurityAuthority authority) {
		if (Objects.isNull(this.authorities)) {
			this.authorities = new HashSet<>();
		}
		if (Objects.nonNull(authority)) {
			authorities.add(authority);
		}
	}

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
