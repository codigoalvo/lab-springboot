package codigoalvo.lab.springboot.security.common.config;

public class CommonConfig {

	public static final String SECRET_PHRASE = "@nGuL4r";

	public static final String[] IGNORE_MATCHERS = {
			"/swagger-resources/**",
			"/swagger-resources/configuration/**",
			"/swagger-ui.html",
			"/swagger-ui.html/**",
			"/v2/api-docs",
			"/webjars/**",
			"/actuator/**",
	};

	public static final String[] PUBLIC_MATCHERS_GET = {

	};

	public static final String[] PUBLIC_MATCHERS_POST = {
			"/error/**",
			"/auth/forgot-password/**",
			"/auth/change-password/**",
			"/jwt/refresh-token/**",
	};

}
