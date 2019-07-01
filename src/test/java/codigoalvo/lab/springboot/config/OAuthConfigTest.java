package codigoalvo.lab.springboot.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class OAuthConfigTest {

	public static void main(String[] args) {
		String senha = "c0dig0@Lv0";
		String senhaCrypt = new BCryptPasswordEncoder().encode(senha);
		System.out.println("Senha: " + senha);
		System.out.println("Senha Criptografada: " + senhaCrypt);
	}

}
