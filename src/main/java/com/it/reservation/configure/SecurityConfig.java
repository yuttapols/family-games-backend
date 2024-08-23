package com.it.reservation.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.it.reservation.encoder.PlainTextPasswordEncoder;
import com.it.reservation.jwt.JwtAuthenticationFilter;
import com.it.reservation.service.UserService;
import com.it.reservation.util.AppConstants;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	@Autowired
	JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	private UserService userService;
	
    private static final String[] AUTH_WHITELIST = {
    		AppConstants.PROJECT_VERSION + "/authentication/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/error",
            AppConstants.PROJECT_VERSION +"/user/checkUsreName",
            AppConstants.PROJECT_VERSION +"/user/saveCustomer",
    };
	
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.requestMatchers(AUTH_WHITELIST)
                        .permitAll().anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

	@Bean
    PasswordEncoder passwordEncoder() {
        return PlainTextPasswordEncoder.getInstance();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        try {
            authProvider.setUserDetailsService(userService.userDetailsService());
            authProvider.setPasswordEncoder(passwordEncoder());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

        return authProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

	
	
}
