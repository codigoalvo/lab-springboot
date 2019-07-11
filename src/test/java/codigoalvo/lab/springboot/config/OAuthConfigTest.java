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
		String original = "@nGuL4r";
		String esperado = "$2a$10$MSPnuhpv0mkLMnbT5v/fH.JKqZmwTxsY9J7T4wHMDtPnyz./.WO8.";
		String resultado = new BCryptPasswordEncoder().encode(original);
		String message = ErrorUtil.getTestMessage(OAuthConfigTest.class, esperado, resultado, false);
		boolean acerto = new BCryptPasswordEncoder().matches(original, resultado);
		log.debug("(" + original + ") Criptografado: " + resultado);
		assertTrue(message, acerto);
	}

}
