package codigoalvo.lab.springboot.security.oauth2.config;

import codigoalvo.lab.springboot.security.common.config.CommonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Profile("oauth2-security")
@Configuration
public class OAuth2AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		//String bCryptSecret = "$2a$10$MSPnuhpv0mkLMnbT5v/fH.JKqZmwTxsY9J7T4wHMDtPnyz./.WO8."; //"@nGuL4r"
		String bCryptSecret = new BCryptPasswordEncoder().encode(CommonConfig.SECRET_PHRASE);
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
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
		endpoints
				//*/
				.tokenStore(tokenStore())
				.tokenEnhancer(tokenEnhancerChain)
				//*/
				.authenticationManager(authenticationManager)
				.userDetailsService(userDetailsService)
				.reuseRefreshTokens(false);
	}

	//*/
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
		accessTokenConverter.setSigningKey(CommonConfig.SECRET_PHRASE);
		return accessTokenConverter;
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	//*/

	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustomTokenEnhancer();
	}

}