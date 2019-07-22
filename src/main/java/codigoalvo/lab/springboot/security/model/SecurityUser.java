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

	private Long id;

	private String email;

	@JsonIgnore
	private String password;

	private String name;

	private Set<SecurityProfile> profiles;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	@Column(unique = true, nullable = false)
	@Size(min = 5, max = 250)
	public String getEmail() {
		return email;
	}

	@NotNull
	@Size(min = 5, max = 250)
	public String getPassword() {
		return password;
	}

	@Size(max = 150)
	public String getName() {
		return name;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "security_user_profile"
			, joinColumns = @JoinColumn(name = "id_user")
			, inverseJoinColumns = @JoinColumn(name = "id_profile")
			, foreignKey = @ForeignKey(name = "fk_user_sup")
			, inverseForeignKey = @ForeignKey(name = "fk_profile_sup")
			, uniqueConstraints = @UniqueConstraint(name = "pk_user_profile", columnNames = {"id_user", "id_profile"})
	)
	public Set<SecurityProfile> getProfiles() {
		return profiles;
	}

	@Transient
	public String getLogin() {
		return getEmail();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.setEmail(login);
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
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
