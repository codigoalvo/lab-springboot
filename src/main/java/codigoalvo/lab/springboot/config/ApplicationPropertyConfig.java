package codigoalvo.lab.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.FileReader;

@Slf4j
@ConfigurationProperties("codigoalvo")
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

    public Security getSecurity() {
        return security;
    }

    private String allowedOrigin = "";

    public String getAllowedOrigin() {
        return allowedOrigin;
    }

    public void setAllowedOrigin(String allowedOrigin) {
        this.allowedOrigin = allowedOrigin;
    }

    public static class Security {
        private boolean enableHttps;

        public boolean isEnableHttps() {
            return enableHttps;
        }

        public void setEnableHttps(boolean enableHttps) {
            this.enableHttps = enableHttps;
        }
    }

}
