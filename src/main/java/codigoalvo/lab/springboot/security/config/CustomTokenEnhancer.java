package codigoalvo.lab.springboot.security.config;

import codigoalvo.lab.springboot.security.adapter.UserDetailsAdapter;
import codigoalvo.lab.springboot.security.service.SecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

@Profile("oauth2-security")
public class CustomTokenEnhancer implements TokenEnhancer {

	@Autowired
	SecurityUserService securityUserService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		UserDetailsAdapter userDetailsAdapter = securityUserService.getUserDetailsAdapterFromAuthentication(authentication);

		Map<String, Object> addInfo = new HashMap<>();
		addInfo.put("nome", userDetailsAdapter.getSecurityUser().getName());

		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
		return accessToken;
	}

}
