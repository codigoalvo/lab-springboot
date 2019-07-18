package codigoalvo.lab.springboot.security.type.converter;

import codigoalvo.lab.springboot.security.type.TipoPerfil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * https://www.baeldung.com/jackson-deserialization
 */

public class TipoPerfilSerializer extends StdSerializer<TipoPerfil> {

	public TipoPerfilSerializer() {
		super(TipoPerfil.class);
	}

	@Override
	public void serialize(TipoPerfil tipoPerfil, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeStartObject();
		jsonGenerator.writeFieldName("key");
		jsonGenerator.writeString(tipoPerfil.getKey());
		jsonGenerator.writeFieldName("value");
		jsonGenerator.writeString(tipoPerfil.getValue());
		jsonGenerator.writeEndObject();
	}

}
