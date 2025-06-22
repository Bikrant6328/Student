package ProjectUpdated.NewProject.jwtServices;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import ProjectUpdated.NewProject.entity.Student;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private String secretKey = "";

	public JwtService() {
		KeyGenerator keyGen;
		try {
			keyGen = KeyGenerator.getInstance("hmacSHA256");
			SecretKey sk = keyGen.generateKey();
			secretKey = java.util.Base64.getEncoder().encodeToString(sk.getEncoded());

		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();
		// Set the expiration time to 15 minutes
		long expirationTimeMillis = 1 * 60 * 1000; // 15 minutes in milliseconds
		Date issuedAt = new Date(System.currentTimeMillis());
		Date expirationDate = new Date(System.currentTimeMillis() + expirationTimeMillis);
		return Jwts.builder().setClaims(claims) // Use setClaims instead of claims()
				.setSubject(username).setIssuedAt(issuedAt).setExpiration(expirationDate) // Set the expiration date
				.signWith(getKey()) // Ensure getKey() returns a valid key
				.compact();
	}

	private SecretKey getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);

		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String extractUsername(String token) {

		return extractClaim(token, Claims::getSubject);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claim = extractAllClaims(token);
		return claimResolver.apply(claim);
	}

	private Claims extractAllClaims(String token) {

		return Jwts.parser().setSigningKey(getKey()).build().parseSignedClaims(token).getPayload();

	}

	public boolean validateToken(String token, UserDetails userDeatils) {
		final String userName = extractUsername(token);

		return (userName.equals(userDeatils.getUsername()) && !isTokenExpired(token));
	}

	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
}
