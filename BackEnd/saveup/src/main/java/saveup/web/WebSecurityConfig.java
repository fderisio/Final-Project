package saveup.web;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/", "/index.html", "/favicon.ico", "/css/**", "/images/**", "/js/**",
			"/webjars/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.mvcMatcher("/user/**")
				.authorizeRequests()
					.mvcMatchers(GET,  "/user/**").permitAll()
					.mvcMatchers(POST, "/user").permitAll()
					.mvcMatchers(      "/user/**").permitAll()
					.and()

			.mvcMatcher("/expenses/**")
				.authorizeRequests()
					.mvcMatchers(GET,  "/expenses/**").permitAll()
					.mvcMatchers(POST, "/expenses").permitAll()
					.mvcMatchers(      "/expenses/**").denyAll()
					.and()

			.mvcMatcher("/**")
				.authorizeRequests()
					.mvcMatchers("/**").denyAll()
					.and()

			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()

			.csrf()
				.disable()

			.httpBasic();
		// @formatter:on
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
			}
		};
	}
	
}