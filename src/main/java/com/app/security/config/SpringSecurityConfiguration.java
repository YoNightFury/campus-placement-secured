package com.app.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.security.filter.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	/**
	 * @implNote the user details service called by spring to get the credentials
	 *           using the username
	 */

	@Autowired
	UserDetailsService userDetailsService;

	/**
	 * @implNote our custom jwt filter
	 */
	@Autowired
	JwtAuthenticationFilter jwtFilter;

	// password encoder provider
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

	// tell spring to use our user details service and password encoder
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
	}

	// authenication manager bean
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	/**
	 * @implNote first enable the cors policy. disable csrf. configure the role
	 *           based filters. make session stateless. add our custom jwt
	 *           authentication before the springs authentication filter class.
	 *           we will do unauthorised exception handling in exception handler instead
	 *           of implementing AuthenticationEntryPoint class
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and() // enable cross origin
				.csrf().disable() // disable csrf
				// applying filters based on roles
				.authorizeRequests() //
				.antMatchers("/admin/**").hasRole("ADMIN") // ADMIN ACCESS
				.antMatchers("/student/**").hasRole("STUDENT") // STUDENT ACCESS
				.antMatchers("/registration/credential/**").hasRole("REGISTERING")  // IF STUDENT HAS NOT SET CREDS
				.antMatchers("/registration/**", "/views/**","/login").permitAll() // ALL ACCESS
				.anyRequest().authenticated() // all authenticated access
				// make session stateless
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//
				// configure our custom jwt filter
				// add our custom filter which will do jwt authentication before spring's
				.and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	}

}
