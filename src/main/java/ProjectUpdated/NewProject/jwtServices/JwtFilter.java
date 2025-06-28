package ProjectUpdated.NewProject.jwtServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import ProjectUpdated.NewProject.serviceImpl.CustomUserDetailsService;
import ProjectUpdated.NewProject.serviceImpl.StudentServiceImpl;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	@Autowired
	private JwtService jwtService;
	@Autowired
    private ApplicationContext applicationContext;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException, java.io.IOException {

		String authHeader = request.getHeader("Authorization");
		String token = null;
		String username = null;

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			username = jwtService.extractUsername(token);

		}
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
		    UserDetails userDeatils = applicationContext.getBean(CustomUserDetailsService.class).loadUserByUsername(username);
		    
		    if(jwtService.isTokenValid(token,userDeatils)) {
		    	UsernamePasswordAuthenticationToken authToken =
		    			                            new UsernamePasswordAuthenticationToken(userDeatils,null,userDeatils.getAuthorities());
		    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		    SecurityContextHolder.getContext().setAuthentication(authToken);
		    
		    }
		    
		}
		filterChain.doFilter(request, response);
	 

	}
}
