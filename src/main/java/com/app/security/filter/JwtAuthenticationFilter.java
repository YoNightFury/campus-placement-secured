package com.app.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.security.utils.JwtUtils;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	JwtUtils jwtUtils;

	/**
	 * @apiNote will intercept all the authentication request if the user tries to
	 *          access protected url based on the roles
	 * 
	 *          we will have to extract the jwt from the request and add the user
	 *          details authenticated token object in security context
	 * 
	 */

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = jwtUtils.extractJwtFromRequest(request);
		if (jwt != null) {
			// create a spring user from the jwt
			UserDetails curUser = jwtUtils.authenticateJwt(jwt);

			// create a spring user token and store in the security context
			// no need to store the password

			UsernamePasswordAuthenticationToken userAuthToken = new UsernamePasswordAuthenticationToken(curUser, "",
					curUser.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(userAuthToken);

		}
		// spring will check the filters based on the role like /register if any user,
		// /login for any user
		// /admin for only admin roles /student for only student roles
		// to do so call the filterchains method
		filterChain.doFilter(request, response);

	}

}
