package codigoalvo.lab.springboot.security.type.converter;

import codigoalvo.lab.springboot.security.type.TipoPerfil;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter(autoApply = true)
public class TipoPerfilConverter implements AttributeConverter<TipoPerfil, String> {

	@Override
	public String convertToDatabaseColumn(TipoPerfil tipo) {
		return Objects.toString(tipo.getKey());
	}

	@Override
	public TipoPerfil convertToEntityAttribute(String tipoStr) {
		return TipoPerfil.staticGetByKey(tipoStr);
	}

}