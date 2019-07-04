package codigoalvo.lab.springboot.config;


import codigoalvo.lab.springboot.util.ErrorUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;

@Slf4j
public class OAuthConfigTest {

	@Test
	public void testarSecretCrypt() {
		String original = "c0dig0@Lv0";
		String esperado = "$2a$10$ygagNzsQUV2L6CE4pd4X.uFNqLaQI0vrs9qzKGPiCeG15TE.GFXxS";
		String resultado = new BCryptPasswordEncoder().encode(original);
		String message = ErrorUtil.getTestMessage(OAuthConfigTest.class, esperado, resultado, false);
		boolean acerto = new BCryptPasswordEncoder().matches(original, resultado);
		log.debug("(" + original + ") Criptografado: " + resultado);
		assertTrue(message, acerto);
	}

}
