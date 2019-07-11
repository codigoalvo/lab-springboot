package codigoalvo.lab.springboot;

import codigoalvo.lab.springboot.config.LabSpringbootConfig;
import codigoalvo.lab.springboot.type.TipoPerfil;
import codigoalvo.lab.springboot.util.ErrorUtil;
import codigoalvo.lab.springboot.util.JsonUtil;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StartApplicationTests {

	private static final boolean LOG_MSGS = true;

	@Autowired
	LabSpringbootConfig config;

	@Test
	public void testTipoPerfilSerialization() {
		String esperado = "{\"key\":\"U\",\"value\":\"Usuário\"}";
		Module module = config.customEntitySerializerDeserializer();
		String resultado = JsonUtil.toJson(TipoPerfil.USUARIO, module);
		String message = ErrorUtil.getTestMessage(StartApplicationTests.class, esperado, resultado, LOG_MSGS);
		assertEquals(message, esperado, resultado);
	}

	@Test
	public void testTipoPerfilDeserialization() throws IOException {
		String tipoStr = "{\"key\":\"U\"}";
		Module module = config.customEntitySerializerDeserializer();
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(module);
		TipoPerfil perfil = mapper.readValue(tipoStr, TipoPerfil.class);
		String esperado = Objects.toString(TipoPerfil.USUARIO);
		String resultado = Objects.toString(perfil);
		String message = ErrorUtil.getTestMessage(StartApplicationTests.class, esperado, resultado, LOG_MSGS);
		assertEquals(message, esperado, resultado);
	}

}
