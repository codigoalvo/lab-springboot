package codigoalvo.lab.springboot.security.config;

import codigoalvo.lab.springboot.security.adapter.UserDetailsAdapter;
import codigoalvo.lab.springboot.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {

	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		UserDetailsAdapter userDetailsAdapter = customUserDetailsService.getUserDetailsAdapterFromAuthentication(authentication);

		Map<String, Object> addInfo = new HashMap<>();
		addInfo.put("nome", userDetailsAdapter.getSecurityUser().getName());

		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
		return accessToken;
	}

}
