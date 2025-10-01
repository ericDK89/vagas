package br.com.eric.vagas.providers;

import br.com.eric.vagas.modules.candidate.dto.CandidateTokenDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTCandidateProvider {
  @Value("${security.token.secret.candidate}")
  private String secretKeyCandidate;

  public CandidateTokenDTO createCandidateToken(String id) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secretKeyCandidate);
      Instant expiresIn = Instant.now().plus(Duration.ofDays(7));
      ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
      LocalDateTime expiresInLocalDateTime = LocalDateTime.ofInstant(expiresIn, zoneId);

      String token =
          JWT.create()
              .withIssuer("auto0Candidate")
              .withSubject(id)
              .withExpiresAt(expiresIn)
              .withClaim("roles", List.of("candidate"))
              .sign(algorithm);

      return new CandidateTokenDTO(token, expiresInLocalDateTime);

    } catch (JWTCreationException e) {
      System.err.println(e.getMessage());
      return null;
    }
  }

  public DecodedJWT validateCandidateToken(String header) {
    String token = header.replace("Bearer ", "").trim();

    Algorithm algorithm = Algorithm.HMAC256(secretKeyCandidate);

    try {
      return JWT.require(algorithm).build().verify(token);
    } catch (JWTVerificationException e) {
      System.err.println(e.getMessage());
      return null;
    }
  }
}
