package com.arsyux.arrow.config;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

//import com.arsyux.arrow.security.UserDetailsServiceImpl;

// 시큐리티 설정 클래스
@Configuration
@EnableWebSecurity
public class ArrowWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationFailureHandler customFailureHandler;
	
	// 시큐리티 권한 제어
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/webjars/**", "/js/**", "/image/**", "/css/**", "/font/**", "/summernote/**", "/message/**", "/", "/adm", "/securitylogin", "/logout", "/post/**", "/exhibition/view/**","/files/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/adm").failureHandler(customFailureHandler)
			.loginProcessingUrl("/securitylogin")
			.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/")
			.and()
			.csrf().disable();
	}
	
}
