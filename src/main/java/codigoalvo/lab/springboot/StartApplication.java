package codigoalvo.lab.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}

	//TODO: Testar se isso funciona sem o CorsFilter quando tiver um client para testar
	/*/
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedOrigins(CorsFilter.ORIGIN_PERMITIDA);
			}
		};
	}
	//*/

}
