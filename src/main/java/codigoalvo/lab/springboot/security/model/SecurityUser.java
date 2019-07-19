package codigoalvo.lab.springboot.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "security_user")
public class SecurityUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	@Size(min = 5, max = 250)
	private String email;

	@JsonIgnore
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
	private Set<SecurityProfile> profiles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Transient
	public String getLogin() {
		return getEmail();
	}

	public void setLogin(String login) {
		this.setEmail(login);
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<SecurityProfile> getProfiles() {
		return profiles;
	}

	public void setProfiles(Set<SecurityProfile> profiles) {
		this.profiles = profiles;
	}

	public void addProfile(SecurityProfile profile) {
		if (Objects.isNull(this.profiles)) {
			this.profiles = new HashSet<>();
		}
		if (Objects.nonNull(profile)) {
			profiles.add(profile);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SecurityUser that = (SecurityUser) o;
		return Objects.equals(getLogin(), that.getLogin());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getLogin());
	}

	@Override
	public String toString() {
		return getLogin();
	}

}
