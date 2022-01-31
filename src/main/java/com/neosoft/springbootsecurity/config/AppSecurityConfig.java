package com.neosoft.springbootsecurity.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.neosoft.springbootsecurity.service.UserServiceImpl;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	//https://stackoverflow.com/questions/60848619/consider-defining-a-bean-of-type-org-springframework-security-crypto-bcrypt-bcr?noredirect=1&lq=1
	
//	@Autowired
//	private Encoder encoder;
//
//	@Autowired
//	private DataSource dataSource;
	
	@Autowired
	UserServiceImpl userService;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;



	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userService);
//		auth.
//			jdbcAuthentication()
//				.usersByUsernameQuery(usersQuery)
//				.authoritiesByUsernameQuery(rolesQuery)
//				.dataSource(dataSource)
//				.passwordEncoder(encoder.passwordEncoder());
		
		
		
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/login").permitAll()
		.antMatchers("/ragister","/registeruser").permitAll()
		.antMatchers("/rest/**").hasAuthority("ADMIN")
		.antMatchers("/rest/user/*").hasAuthority("USER")
		.antMatchers("/swagger-ui/*").permitAll()
		.antMatchers("/token").permitAll()
		.anyRequest()
		.authenticated().and().csrf().disable().formLogin()
		.loginPage("/login").failureUrl("/login?error=true")
		.defaultSuccessUrl("/success")
		.usernameParameter("email")
		.passwordParameter("password")
		.and().logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout=true")
		.invalidateHttpSession(true).and().exceptionHandling()
		.accessDeniedPage("/access_denied")
		.authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
            
	}


	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/resources/*", "/static/", "/css/", "/js/", "/img/*");
	}
	
	
	@Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
	}
	
	

}
