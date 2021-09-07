package tacos.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		 auth
//		 .inMemoryAuthentication()
//		 .withUser("gaurav")
//		 .password("{noop}chauhan")
//		 .authorities("ROLE_USER")
//		 .and()
//		 .withUser("woody")
//		 .password("bullseye")
//		 .authorities("ROLE_USER");
//	}
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder encode() {
		return new StandardPasswordEncoder("53cr3t");
	}
	
	@Override
		protected void configure(HttpSecurity http) throws Exception {
//			http
//				.authorizeRequests()
//				.antMatchers("/design", "/login")
//				.hasRole("ROLE_USER")
//				.antMatchers("/", "/**")
//				.permitAll();
		
		http
		    .authorizeRequests()
		    .antMatchers("/design", "/orders")
		      .access("hasRole('ROLE_USER')")
		    .antMatchers("/","/**")
		      .access("permitAll()")
		    .and()
		      .formLogin()
		      .loginPage("/login")
			.and()
	          .logout()
	          .logoutSuccessUrl("/");
		}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//			.jdbcAuthentication()
//			.dataSource(dataSource)
		
//		auth
//		 .jdbcAuthentication()
//		 .dataSource(dataSource)
//		 .usersByUsernameQuery(
//		 "select username, password, enabled from Users " +
//		 "where username=?")
//		 .authoritiesByUsernameQuery(
//		 "select username, authority from UserAuthorities " +
//		 "where username=?");
		
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(encode());
			
	}
}