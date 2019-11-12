package codigoalvo.lab.springboot;

import codigoalvo.lab.springboot.config.ApplicationPropertyConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.event.EventListener;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(ApplicationPropertyConfig.class)
public class StartApplication {

	@Autowired
	ApplicationPropertyConfig applicationPropertyConfig;

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void logLoadedConfigurations() {
		log.info("*********************************************************************    Application Startetd !!!    *********************************************************************");
		log.debug("?? - ApplicationPropertyConfig: " + applicationPropertyConfig);
		log.debug("?? pom.xml -> ApplicationId: " + this.applicationPropertyConfig.getApplicationId());
		log.debug("?? pom.xml -> ApplicationGroupId: " + this.applicationPropertyConfig.getApplicationGroupId());
		log.debug("?? pom.xml -> ApplicationArtifactId: " + this.applicationPropertyConfig.getApplicationArtifactId());
		log.debug("?? pom.xml -> ApplicationVersion: " + this.applicationPropertyConfig.getApplicationVersion());
		log.info("*********************************************************************    Application Startetd !!!    *********************************************************************");
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
