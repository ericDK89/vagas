package br.com.eric.vagas.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTProvider {

  @Value("${security.token.secret}")
  private String secretKey;

  @Value("${security.token.secret.candidate}")
  private String secretKeyCandidate;

  public void createToken(String id) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secretKey);

      String token =
          JWT.create()
              .withIssuer("auth0")
              .withSubject(id)
              .withExpiresAt(Instant.now().plus(Duration.ofDays(7)))
              .sign(algorithm);

      System.out.println(token);

    } catch (JWTCreationException e) {
      System.err.println(e.getMessage());
    }
  }

  public void createCandidateToken(String id) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secretKeyCandidate);

      String token =
          JWT.create()
              .withIssuer("auto0Candidate")
              .withSubject(id)
              .withExpiresAt(Instant.now().plus(Duration.ofMinutes(10)))
              .withClaim("roles", List.of("candidate"))
              .sign(algorithm);

      System.out.println("candidate token: " + token);

    } catch (JWTCreationException e) {
      System.err.println(e.getMessage());
    }
  }

  public String validateToken(String header) {
    String token = header.replace("Bearer ", "").trim();

    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    try {
      return JWT.require(algorithm).build().verify(token).getSubject();

    } catch (Exception e) {
      System.err.println(e.getMessage());
      return null;
    }
  }
}
