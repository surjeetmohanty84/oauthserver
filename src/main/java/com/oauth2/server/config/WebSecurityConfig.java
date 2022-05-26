package com.oauth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.oauth2.server.service.CustomAuthenticationProvider;
import com.oauth2.server.service.UserDetailsServiceImpl;
@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired
	PasswordEncoder passwordEncoder;
	//@Autowired
	//private UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;
	@Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                        authorizeRequests.anyRequest().authenticated()
                )
             //   .authenticationProvider(authenticationProvider())
                .formLogin(Customizer.withDefaults());

        return http.build();
    }
	@Autowired
	public void bindAuthenticationProvider(AuthenticationManagerBuilder authenticationManagerBuilder) {
		authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider);
	}
	/*
	 * @Bean DaoAuthenticationProvider authenticationProvider() {
	 * DaoAuthenticationProvider authenticationProvider = new
	 * DaoAuthenticationProvider();
	 * authenticationProvider.setPasswordEncoder(passwordEncoder);
	 * authenticationProvider.setUserDetailsService(userDetailsServiceImpl); return
	 * authenticationProvider; }
	 */
}