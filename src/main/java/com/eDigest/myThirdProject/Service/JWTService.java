package com.eDigest.myThirdProject.Service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

	private String secretKey = "";
	
	static String algo = "HmacSHA256";
	
	public JWTService() {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance(algo);
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
				.setExpiration(new Date(System.currentTimeMillis() + 60 *60 *30))
				.signWith(getKey())
				.compact();
	}
	
	private Key getKey() {
		byte[] byteKey = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(byteKey);
	}
	
}
