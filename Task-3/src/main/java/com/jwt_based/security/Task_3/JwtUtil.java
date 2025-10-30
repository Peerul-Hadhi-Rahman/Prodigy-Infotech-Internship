package com.jwt_based.security.Task_3;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private final String SECRET = "}]))Xw]%:cxX5-IpR,U#h\\aZVw62Y[\"N";
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
	
	public String extractRole(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.get("role", String.class);
	}
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
