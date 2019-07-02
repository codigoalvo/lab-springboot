package codigoalvo.lab.springboot.config;

import codigoalvo.lab.springboot.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	//*/
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	//*/

	/*/
	@Primary
	@Override
	@Bean
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return super.userDetailsServiceBean();
	}
	//*/

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		//*/
			.userDetailsService(customUserDetailsService)
			.passwordEncoder(getPasswordEncoder());
		//*/
		/*/
			.inMemoryAuthentication()
			.withUser("usuario@email.com").password("$2a$10$hHr8kI3BRHpweNJdn0Mg.e83mmZKkbzUf9twUbJ4cKmBZRNdf8QFK").roles("USER")
			.and()
			.withUser("admin@email.com").password("$2a$10$hHr8kI3BRHpweNJdn0Mg.e83mmZKkbzUf9twUbJ4cKmBZRNdf8QFK").roles("USER", "ADMIN");
		//*/
	}

}