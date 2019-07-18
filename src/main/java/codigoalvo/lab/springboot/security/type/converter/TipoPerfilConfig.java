package codigoalvo.lab.springboot.security.type.converter;

import codigoalvo.lab.springboot.security.type.TipoPerfil;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TipoPerfilConfig {

	@Bean
	public Module customEntitySerializerDeserializer() {
		SimpleModule module = new SimpleModule();
		module.addDeserializer(TipoPerfil.class, new TipoPerfilDeserializer());
		module.addSerializer(TipoPerfil.class, new TipoPerfilSerializer());
		return module;
	}

}
