package codigoalvo.lab.springboot.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;

@Setter
@Getter
@Slf4j
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "codigoalvo")
public class ApplicationPropertyConfig {

    public ApplicationPropertyConfig() {
        try {
            MavenXpp3Reader reader = new MavenXpp3Reader();
            this.model = reader.read(new FileReader("pom.xml"));
            if (model != null) {
                log.trace("? ApplicationPropertyConfig.MavenXpp3Reader - pom.xml carregado com sucesso: "+this.model.toString());
            }
        } catch (Throwable exc) {
            log.error("Erro ao carregar/exibir informações do pom.xml", exc);
        }
    }

    private Model model;

    public String getApplicationId() {
        return this.model == null ? null : this.model.getId();
    }

    public String getApplicationGroupId() {
        return this.model == null ? null : this.model.getGroupId();
    }

    public String getApplicationArtifactId() {
        return this.model == null ? null : this.model.getArtifactId();
    }

    public String getApplicationVersion() {
        return this.model == null ? null : this.model.getVersion();
    }

    //-----------------------------------------------------------------------------------

    private final Security security = new Security();

    @Setter
    @Getter
    public static class Security {
        private boolean enableHttps;
        private String allowedOrigin = "";

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("{ ");
            sb.append("enableHttps: ").append(enableHttps).append(", ");
            sb.append("allowedOrigin: '").append(allowedOrigin.toString()).append("'");
            sb.append(" }");
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{ ");
        sb.append("security: ").append(security.toString());
        sb.append(" }");
        return sb.toString();
    }

}
