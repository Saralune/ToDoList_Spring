/**
 * 
 */
package fr.fms.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Stagiaires10P
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
        .dataSource(dataSource)
         .usersByUsernameQuery(
                        "select mail as username, password as credentials, active from users where mail=?")

        .authoritiesByUsernameQuery(
                		" SELECT users.mail as username, role.role as role FROM users"+        
                		" INNER JOIN users_role ON users.id = users_role.users_id"+ 
                		" INNER JOIN role ON users_role.role_id = role.id WHERE users.mail =? ")


        .rolePrefix("ROLE_")
        .passwordEncoder(passwordEncoder());
	}

	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.formLogin()
				.loginPage("/login").usernameParameter("mail").passwordParameter("password").defaultSuccessUrl("/readTasks")
				.failureUrl("/login?error=true").permitAll();
		
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/login");

		http.authorizeRequests().antMatchers("/readTasks", "/editTasks").hasRole("USER");

		http.exceptionHandling().accessDeniedPage("/403");
	}
}
