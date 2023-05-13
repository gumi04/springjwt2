package com.example.demogradle.web.config.security;

import com.example.demogradle.model.dto.AuthCredentials;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * The type Jwt filter.
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  /**
   * intento de identificarse
   *
   * @param request  from which to extract parameters and perform the authentication
   * @param response the response, which may be needed if the implementation has to do a
   *                 redirect as part of a multi-stage authentication process (such as OpenID).
   * @return
   * @throws AuthenticationException
   */
  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
                                              HttpServletResponse response) throws AuthenticationException {

    AuthCredentials authCredentials = new AuthCredentials();
    try {
      authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
    } catch (IOException e) {
    }
    UsernamePasswordAuthenticationToken userName = new UsernamePasswordAuthenticationToken(
        authCredentials.getEmail(),
        authCredentials.getPassword(),
        Collections.emptyList()
    );
    return getAuthenticationManager().authenticate(userName);
  }

  /**
   * Default behaviour for successful authentication.
   * Subclasses can override this method to continue the {@link FilterChain} after
   * successful authentication.
   *
   * @param request
   * @param response
   * @param chain
   * @param authResult the object returned from the <tt>attemptAuthentication</tt>
   *                   method.
   * @throws IOException
   * @throws ServletException
   */
  @Override
  protected void successfulAuthentication(HttpServletRequest request,
                                          HttpServletResponse response,
                                          FilterChain chain,
                                          Authentication authResult) throws IOException, ServletException {
    UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
    String token = GenerateJWT.createToken(userDetails.getNombre(), userDetails.getUsername());

    response.addHeader("Authorization", "Bearer " + token);
    response.getWriter().flush();

    super.successfulAuthentication(request, response, chain, authResult);
  }
}
