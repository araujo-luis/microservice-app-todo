package microservices.app.zuulloadbalancer.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class ApiSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.headers().frameOptions().disable();

		http.authorizeRequests()
				.antMatchers(HttpMethod.POST, env.getProperty("micro.authentication-service.login.url")).permitAll()
				.antMatchers(HttpMethod.GET, env.getProperty("micro.users-service.signup.url") + "/email/**").permitAll()
				.antMatchers(HttpMethod.POST, env.getProperty("micro.users-service.signup.url")).permitAll()
				.antMatchers(env.getProperty("micro.zuul-load-balancer.actuator.url")).permitAll()
				.antMatchers(env.getProperty("micro.users-service.actuator.url")).permitAll()
				
				.antMatchers("/users-service/swagger-ui.html").permitAll()
				.antMatchers("/authentication-service/swagger-ui.html").permitAll()
				.anyRequest().authenticated().and().addFilter(new AuthenticationFilter(authenticationManager(), env));

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}
