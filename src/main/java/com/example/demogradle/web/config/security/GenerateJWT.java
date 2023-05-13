package com.example.demogradle.web.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Generate jwt.
 */
public class GenerateJWT {

  private final static String KEY = "skaerjaerhkjwahrjkahejkhakjerkjakjhakjdshfakjsf";
  private final static long TIME = 3600L; //una hora

  /**
   * Create token string.
   *
   * @param nombre the nombre
   * @param email  the email
   * @return the string
   */
  public static String createToken(String nombre, String email) {
    long experitaionTime = TIME * 1000;
    Date experitaionDate = new Date(System.currentTimeMillis() + experitaionTime);
    Map<String, Object> data = new HashMap<>();
    data.put("nombre", nombre);
    return Jwts
        .builder()
        .setSubject(email)
        .setExpiration(experitaionDate)
        .addClaims(data)
        .signWith(Keys.hmacShaKeyFor(KEY.getBytes()))
        .compact();
  }

  /**
   * Gets password authentication token.
   *
   * @param token the token
   * @return the password authentication token
   */
  public static UsernamePasswordAuthenticationToken getPasswordAuthenticationToken(String token) {
    try {
      Claims claims = Jwts
          .parserBuilder()
          .setSigningKey(KEY.getBytes())
          .build()
          .parseClaimsJws(token)
          .getBody();

      String email = claims.getSubject();
      return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
    } catch (JwtException e) {
      throw new JwtException("Error al decodificar token");
    }
  }


}
