package com.hotel_booking_system.Task_5.Service;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;
import com.hotel_booking_system.Task_5.model.Role;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private final String SECRET = "CBQGL5u8LiA2IzvinAyw8qjg7Cq8DRF8xtQm5uOUWxm";
	private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
	
	public String generateToken(String email, Role role) {
		return Jwts.builder()
				.setSubject(email)
				.claim("role", role.name())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 86400000))
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String extractEmail(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();	
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token);
			return true;
		} catch (JwtException e) {
			return false;
		}
	}
}
