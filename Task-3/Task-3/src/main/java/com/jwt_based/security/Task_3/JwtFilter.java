package com.jwt_based.security.Task_3;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	@Autowired 
	private JwtUtil jwtUtil;
	@Autowired
	private UserRepository userRepo;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			String token = authHeader.substring(7);
			if(jwtUtil.validateToken(token)) {
				String email = jwtUtil.extractEmail(token);
				AppUser user = userRepo.findByEmail(email).orElse(null);
				if(user != null) {
					String authority = "ROLE_" + user.getRole().name();
					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken
							(email, null, List.of(new SimpleGrantedAuthority
									(authority)));
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
			}
		}
		filterChain.doFilter(request, response);		
	}

}
