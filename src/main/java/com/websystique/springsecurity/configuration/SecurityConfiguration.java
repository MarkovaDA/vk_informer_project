package com.websystique.springsecurity.configuration;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;
	
        @Autowired
	DataSource dataSource;
        
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder(); //алгоритм шифорвания пароля
	}
	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	    authenticationProvider.setUserDetailsService(userDetailsService);
	    authenticationProvider.setPasswordEncoder(passwordEncoder());
	    return authenticationProvider;
	}
        
        @Bean
        public PersistentTokenRepository persistentTokenRepository() {
            JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
            tokenRepositoryImpl.setDataSource(dataSource);
            return tokenRepositoryImpl;
        }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
          http.csrf().disable();
          
	  http.authorizeRequests()
	  	.antMatchers("/", "/home", "/api/**").permitAll()
	  	.antMatchers("/admin/**","/newuser").access("hasRole('ADMIN')")
	  	//.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                //.antMatchers("/client","/client/**").hasRole("CLIENT")
                //.antMatchers("/employee","/employee/**").hasRole("EMPLOYEE")
                .antMatchers("/user/**").access("hasRole('LECT')")
                .antMatchers("/entrance","/entrance/**").hasAnyRole("ADMIN","LECT","ADRESAT") 
	  	.and().formLogin().loginPage("/login")
	  	.usernameParameter("login").passwordParameter("password")
	  	.and().exceptionHandling().accessDeniedPage("/Access_Denied");  
	}
}
