package edu.bilkent.bilbilet.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	final private JWTUserService jwtUserService;
	final private JWTFilter jwtFilter;

	// @Autowired
	// private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private static final String[] AUTH_WHITELIST = {
		"/v3/api-docs/**",
		"/swagger-ui/**",
		"/api/v1/auth/hello",
		"/api/v1/auth/login",
		"/api/v1/auth/register",
		"/api/v1/auth/register/traveler"
	};

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors();
		http
			.csrf().disable()
			.authorizeHttpRequests((request) -> {
					try {
						request
							.requestMatchers(AUTH_WHITELIST)							
							.permitAll()						
							.anyRequest().authenticated()							
							.and()
							.sessionManagement()
							.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
							.and()
							.authenticationProvider(authenticationProvider());
					} catch (Exception e) {
						e.printStackTrace();
					}					
				
			});
		// http.addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(jwtUserService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

	// @Bean
	// public PasswordEncoder passwordEncoder(int strength) {
	// 	return new BCryptPasswordEncoder(strength);
	// }
}