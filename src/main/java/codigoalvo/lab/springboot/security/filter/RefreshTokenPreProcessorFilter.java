package codigoalvo.lab.springboot.security.filter;

import org.apache.catalina.util.ParameterMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Map;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RefreshTokenPreProcessorFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if (isOAuthGrantTypeRefreshToken(req)) {
			for (Cookie cookie : req.getCookies()) {
				if ("refreshToken".equalsIgnoreCase(cookie.getName())) {
					String refreshToken = cookie.getValue();
					req = new MyServletRequestWrapper(req, refreshToken);
					break;
				}
			}
		}
		chain.doFilter(req, response);
	}

	private boolean isOAuthGrantTypeRefreshToken(HttpServletRequest req) {
		return req != null && req.getRequestURI() != null
				&& req.getCookies() != null
				&& req.getRequestURI().contains("oauth/token")
				&& "refresh_token".equalsIgnoreCase(req.getParameter("grant_type"))
				;
	}

	static class MyServletRequestWrapper extends HttpServletRequestWrapper {

		private String refreshToken;

		public MyServletRequestWrapper(HttpServletRequest request, String refreshToken) {
			super(request);
			this.refreshToken = refreshToken;
		}

		@Override
		public Map<String, String[]> getParameterMap() {
			ParameterMap<String, String[]> map = new ParameterMap<>(getRequest().getParameterMap());
			map.put("refresh_token", new String[]{this.refreshToken});
			map.setLocked(true);
			return map;
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
