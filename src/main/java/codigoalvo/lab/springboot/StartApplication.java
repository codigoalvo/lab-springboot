package codigoalvo.lab.springboot;

import codigoalvo.lab.springboot.config.ApplicationPropertyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationPropertyConfig.class)
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
