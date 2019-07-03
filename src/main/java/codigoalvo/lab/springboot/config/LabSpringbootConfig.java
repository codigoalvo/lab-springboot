package codigoalvo.lab.springboot.config;

import codigoalvo.lab.springboot.type.TipoPerfil;
import codigoalvo.lab.springboot.type.converter.TipoPerfilDeserializer;
import codigoalvo.lab.springboot.type.converter.TipoPerfilSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class LabSpringbootConfig {

	@Bean
	public Module customEntitySerializerDeserializer() {
		SimpleModule module = new SimpleModule();
		module.addDeserializer(TipoPerfil.class, new TipoPerfilDeserializer());
		module.addSerializer(TipoPerfil.class, new TipoPerfilSerializer());
		return module;
	}

}
