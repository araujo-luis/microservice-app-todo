package microservices.app.authenticationservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import microservices.app.authenticationservice.service.UserService;
import microservices.app.authenticationservice.service.impl.AuthenticationServiceImpl;


@Configuration
@EnableWebSecurity
public class ApiSecurity extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserService userService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/authentication").permitAll().anyRequest().authenticated();
		http.headers().frameOptions().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}
	
	
	@Bean
	public AuthenticationServiceImpl athentication() throws Exception {
		return new AuthenticationServiceImpl();
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	@Bean
	public PasswordEncoder pe() {
		return NoOpPasswordEncoder.getInstance();
	}

}