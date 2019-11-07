package codigoalvo.lab.springboot.security.controller;

import codigoalvo.lab.springboot.config.ApplicationPropertyConfig;
import codigoalvo.lab.springboot.security.service.SecurityUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Profile("oauth2-security")
@Slf4j
@RestController
@EnableResourceServer
public class OAuth2Controller {

	@Autowired
	private SecurityUserService securityUserService;

	@Autowired
	private ApplicationPropertyConfig applicationPropertyConfig;

	@DeleteMapping("/revoke")
	public void revoke(HttpServletRequest req, HttpServletResponse resp) {
		log.debug("DELETE /revoke");
		Cookie cookie = new Cookie("refreshToken", null);
		cookie.setHttpOnly(true);
		cookie.setSecure(applicationPropertyConfig.getSecurity().isEnableHttps());
		cookie.setPath(req.getContextPath() + "/oauth/token");
		cookie.setMaxAge(0);
		resp.addCookie(cookie);
		resp.setStatus(HttpStatus.NO_CONTENT.value());
	}

	@RequestMapping(value = {"/user"}, produces = "application/json")
	public Map<String, Object> user(OAuth2Authentication user) {
		log.info("GET /user -> " + user);
		Map<String, Object> userInfo = new HashMap<>();
		userInfo.put("userDetails", securityUserService.getAuthenticated());
		userInfo.put("authorities", AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));
		return userInfo;
	}

}
