package codigoalvo.lab.springboot.security.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

	public static String encryptPassword(String textPassword) {
		return new BCryptPasswordEncoder().encode(textPassword);
	}

}
