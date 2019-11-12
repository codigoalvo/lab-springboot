package codigoalvo.lab.springboot.security.basic;

import codigoalvo.lab.springboot.security.common.config.CommonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Profile("basic-security")
@Configuration
@EnableWebSecurity
public class BasicWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// Desabilita o CSRF
		httpSecurity.csrf().disable();

		httpSecurity.authorizeRequests()
				.antMatchers(HttpMethod.GET, CommonConfig.PUBLIC_MATCHERS_GET).permitAll()
				.antMatchers(HttpMethod.POST, CommonConfig.PUBLIC_MATCHERS_POST).permitAll()
				.anyRequest().authenticated().and().httpBasic();

		// Não cria sessão de usuário no backend
		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

}
