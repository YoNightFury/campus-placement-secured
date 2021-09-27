package com.app.security.utils;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.app.pojos.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtils {

	/**
	 * secret key value taken from spring application.properties using spel
	 */
	@Value("${jwt.secret}")
	private String key;

	/**
	 * Exipiration time value taken from spring application.properties using spel
	 */
	@Value("${jwt.jwtExpirationInMs}")
	private int expInMillis;

	/**
	 * @apiNote create a jwt token using username, id and whether the user isAdmin
	 *          or isStudent and issued at and expiration date
	 * 
	 */

	public String generateJwt(Integer id, String username, Role role) {
		Map<String, Object> claims = new HashMap<>();

		if (role == Role.ADMIN)
			claims.put("isAdmin", true);
		else if (role == Role.STUDENT)
			claims.put("isStudent", true);

		Date now = new Date(System.currentTimeMillis());
		Date exp = new Date(System.currentTimeMillis() + expInMillis);

		return Jwts.builder() // building jwt
				.setClaims(claims) // set the claims always first otherwise entire payload is reset
				.setIssuedAt(now) // set when issued
				.setExpiration(exp) // set when it will expire
				.setId(id.toString()) // set the id
				.setSubject(username) // set the subject that is the username
				.signWith(SignatureAlgorithm.HS512, key).compact(); // set the secret key and return the string
	}

	private Claims getClaimsBody(String jwt) {

		return Jwts.parser() // create a jwt parser
				.setSigningKey(key) // set the signing key
				.parseClaimsJws(jwt) // parse the claims from the jwt
				.getBody(); // get the payload body i.e. the claims
	}

	public String extractJwtFromRequest(HttpServletRequest req) {

		String jwt = null;
		// request has the jwt in the header Authorization
		// after the 'Bearer' the substring starting from 7 index
		String bearerToken = req.getHeader("Authorization");
		// Bearer 234i2ub53252b54jib325434#$%j4n3534b
		// if it is null then set cannot set security context maybe a guest user
		if (StringUtils.hasText(bearerToken)) {
			jwt = bearerToken.substring(7);
		}
		return jwt;
	}

	public UserDetails authenticateJwt(String jwt) {

		Claims claims = getClaimsBody(jwt);

		// extract the username and roles

		String username = claims.getSubject();
		// we have stored the claims as boolean
		Boolean adminClaim = claims.get("isAdmin", Boolean.class);
		Boolean studentClaim = claims.get("isStudent", Boolean.class);
		// list of authorities or roles
		List<? extends GrantedAuthority> roles = null;
		if (adminClaim != null && adminClaim)
			roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_" + Role.ADMIN));
		if (studentClaim != null && studentClaim)
			roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_" + Role.STUDENT));

		return new User(username, "", roles);

	}

	// get the id from the jwt
	public int getUserIdFromJwt(String jwt) {
		String id = getClaimsBody(jwt).getId();
		return Integer.parseInt(id);
	}

}
