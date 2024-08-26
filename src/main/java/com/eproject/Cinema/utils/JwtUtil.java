package com.eproject.Cinema.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.eproject.Cinema.entities.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
	public static final String SECRET_KEY = "T12210E1";

	public String generateToken(User user) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("userName", user.getUserName());
		claims.put("role", user.getRole());
		return createToken(claims, user.getUserName());
	}

	private String createToken(Map<String, Object> claims, String userName) {
		Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());
		return JWT.create()
				.withSubject(userName)
				.withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000 * 24))
				.withClaim("UserInfo", claims)
				.sign(algorithm);
	}
}