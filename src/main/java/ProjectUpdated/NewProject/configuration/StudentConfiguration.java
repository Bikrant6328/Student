package ProjectUpdated.NewProject.configuration;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import ProjectUpdated.NewProject.jwtServices.JwtFilter;



@EnableWebSecurity
@Configuration
public class StudentConfiguration {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtFilter jwtFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors(cors -> cors.configurationSource(corsConfigurationSource())); // Use the defined CorsConfigurationSource
		http.csrf(csrf -> csrf.disable());

		http.authorizeHttpRequests(auth -> {
			auth.requestMatchers("/NewProject-0.0.1-SNAPSHOT").permitAll()
			.requestMatchers("/index.html").permitAll()
			.requestMatchers("/assets").permitAll()
			.requestMatchers("/api/student/login").permitAll()
			.requestMatchers("/user/login").permitAll()
			.requestMatchers("/api/teacher/login").permitAll()
			.requestMatchers("/api/student/register").permitAll()
		    .requestMatchers("/api/teacher/register").permitAll()
			.requestMatchers("/api/courses/create").permitAll()
			.requestMatchers("/api/test").permitAll()
//		    .requestMatchers("/api/enrollments/enroll").permitAll()	    
//			.requestMatchers("/user/*").permitAll() // Your Angular login endpoint
//			.requestMatchers("/index.html").permitAll()
//			.requestMatchers("/static/**").permitAll()
//			.requestMatchers("/assets/**").permitAll()
//			.requestMatchers("/styles.css").permitAll()
//			.requestMatchers("/css/**").permitAll()
		    .anyRequest().authenticated();
		});

     	http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
        // For handling unauthorized access without redirecting to a login page
        http.exceptionHandling(exceptions -> exceptions
            .authenticationEntryPoint(new HttpStatusEntryPoint(org.springframework.http.HttpStatus.UNAUTHORIZED))
        );

		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200")); // Allow your Angular app
        //configuration.setAllowedOrigins(Arrays.asList("http://localhost:8081/"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allow specific methods
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Allow specific headers
        configuration.setAllowCredentials(true); // Allow credentials if needed
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply CORS configuration to all endpoints
        return source;
    }
	
	
 
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return  config.getAuthenticationManager();
	}


	
}
