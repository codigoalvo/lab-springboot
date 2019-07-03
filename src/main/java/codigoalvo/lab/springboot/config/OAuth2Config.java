package codigoalvo.lab.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		String bCryptSecret = "$2a$10$ygagNzsQUV2L6CE4pd4X.uFNqLaQI0vrs9qzKGPiCeG15TE.GFXxS"; //"c0dig0@Lv0"
		clients.inMemory()
				.withClient("angular")
				.secret(bCryptSecret)
				.authorizedGrantTypes("refresh_token", "password", "client_credentials")
				.scopes("webclient", "mobileclient")
				.accessTokenValiditySeconds(120)
		;
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
				.authenticationManager(authenticationManager)
				.userDetailsService(userDetailsService);
	}

}