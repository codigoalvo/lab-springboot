package codigoalvo.lab.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		String bCryptSecret = "$2a$10$MSPnuhpv0mkLMnbT5v/fH.JKqZmwTxsY9J7T4wHMDtPnyz./.WO8."; //"@nGuL4r"
		clients.inMemory()
				.withClient("angular")
				.secret(bCryptSecret)
				.authorizedGrantTypes("refresh_token", "password")
				.scopes("read", "write")
				.accessTokenValiditySeconds(60 * 30) //30 minutos
				.refreshTokenValiditySeconds(60 * 60 * 8) // 8 horas
		;
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
				.authenticationManager(authenticationManager)
				//.userDetailsService(userDetailsService) //TODO: Ver se remover isso causará algum problema.
				//.reuseRefreshTokens(false)
		;
	}

}