package initializ.blog.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenHelper {

	public static final long JWT_TOKEN_VALIDITY= 5*60*60;//(duration of a token validity in mili  -second)
	private String secret ="jwtTokenKey";
	
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);//retrieve username from token
	}
	
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token,Claims::getExpiration);//retrieve expiration date from JWT token
	}
	public <T> T getClaimFromToken(String token,Function<Claims,T> claimsResolver) {
		final Claims claims =getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}         
	private Claims getAllClaimsFromToken(String token) { //to retrieve info we need secret key
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	private Boolean isTokenExpired(String token) {// check if token is expired
		final Date expiration=getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	public String generateToken(UserDetails userDetails) {
		Map<String,Object>claims=new HashMap<>();
		return doGenerateToken(claims,userDetails.getUsername() );}
	
	private String  doGenerateToken(Map<String, Object> claims, String username) {
		return Jwts.builder()
		.setClaims(claims)
		.setSubject(username)
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY *1000))
		.signWith(SignatureAlgorithm.HS512, secret)
		.compact();
		}
	
	public Boolean validateToken(String token, UserDetails userDetails) { //validate token
		final String username= getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
	}
	
	
   

}
