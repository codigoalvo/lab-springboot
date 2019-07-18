package codigoalvo.lab.springboot.security.type;

import codigoalvo.lab.springboot.type.Tipo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * https://thepracticaldeveloper.com/2018/07/31/java-and-json-jackson-serialization-with-objectmapper/
 */

//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoPerfil implements Tipo<TipoPerfil, String, String> {

	@JsonIgnore
	INDEFINIDO("IDF", "Indefinido"), USUARIO("USU", "Usuário"), ADMIN("ADM", "Administrador");

	@JsonIgnore
	public static final TipoPerfil[] LST = {USUARIO, ADMIN};

	@JsonProperty
	private String key;

	@JsonProperty
	private String value;

	private TipoPerfil(String key, String value) {
		this.key = key;
		this.value = value;
	}

	@JsonIgnore
	public static TipoPerfil staticGetByKey(String key) {
		return INDEFINIDO.getByKey(key);
	}

	@Override
	public String getKey() {
		return this.key;
	}

	@Override
	public String getValue() {
		return this.value;
	}

	@JsonIgnore
	@Override
	public TipoPerfil[] getAll() {
		return LST;
	}

	@JsonIgnore
	@Override
	public TipoPerfil getDefault() {
		return null;
	}

}
