package codigoalvo.lab.springboot.type.converter;

import codigoalvo.lab.springboot.type.TipoPerfil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;

/**
 * https://www.baeldung.com/jackson-deserialization
 */
public class TipoPerfilDeserializer extends StdDeserializer<TipoPerfil> {

	public TipoPerfilDeserializer() {
		super(TipoPerfil.class);
	}

	@Override
	public TipoPerfil deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
		JsonNode node = jsonParser.getCodec().readTree(jsonParser);
		String key = ((TextNode) node.get("key")).textValue();
		return key == null ? null : TipoPerfil.staticGetByKey(key);
	}

}
