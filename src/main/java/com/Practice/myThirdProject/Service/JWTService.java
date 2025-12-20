package com.Practice.myThirdProject.Service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Service
public class JWTService {

	private static String secretKey = "";
	
	
	
	public JWTService() {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk = keyGen.generateKey();
			secretKey =Base64.getEncoder().encodeToString(sk.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String generateToken(String userName){
		
		Map<String,Object> claims = new HashMap<>();
		 
		return Jwts.builder()
				.addClaims(claims)
				.setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 60*60*30))
				.signWith(getKey())
				.compact();
	}
	
	private static Key getKey() {
		byte[] byteKey = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(byteKey);
	}
	public static String extractUserName(String token) {
		// TODO Auto-generated method stub
		return extractClaims(token,Claims::getSubject);
	}
	private static <T> T extractClaims(String token, Function<Claims,T> ClaimResolver) {
		// TODO Auto-generated method stub
		final Claims claims =extractAllClaims(token);
		return ClaimResolver.apply(claims);
	}
	
	private static Claims extractAllClaims(String token) throws SignatureException, ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, IllegalArgumentException {
		// TODO Auto-generated method stub
			return Jwts.parserBuilder()
					.setSigningKey(getKey())
					.build()
					.parseClaimsJws(token)
					.getBody();
		
	}
	public boolean validateToken(String token, UserDetails userDetails) {
		// TODO Auto-generated method stub
		String userName = extractUserName(token);
		return (userName.equalsIgnoreCase(userDetails.getUsername()) && !isTokenExpired(token));
	}
	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		
		return extractExpirationTime(token).before(new Date());
	}
	private Date extractExpirationTime(String token) {
		// TODO Auto-generated method stub
		return extractClaims(token, Claims:: getExpiration);
	}
	
}
