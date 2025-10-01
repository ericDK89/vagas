package br.com.eric.vagas.modules.candidate.useCases;

import br.com.eric.vagas.modules.candidate.dto.AuthRequestCandidateDTO;
import br.com.eric.vagas.modules.candidate.dto.CandidateTokenDTO;
import br.com.eric.vagas.modules.candidate.entities.CandidateEntity;
import br.com.eric.vagas.modules.candidate.repositories.CandidateRepository;
import br.com.eric.vagas.providers.JWTCandidateProvider;
import javax.naming.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthCandidateUseCase {

  @Autowired
  JWTCandidateProvider jwtCandidateProvider;
  @Autowired private CandidateRepository candidateRepository;
  @Autowired private PasswordEncoder passwordEncoder;

  public CandidateTokenDTO execute(AuthRequestCandidateDTO authRequestCandidateDTO)
      throws AuthenticationException {
    CandidateEntity candidate =
        candidateRepository
            .findByUsername(authRequestCandidateDTO.username())
            .orElseThrow(() -> new UsernameNotFoundException("Username/Password incorrect"));

    boolean passwordsMatches =
        passwordEncoder.matches(authRequestCandidateDTO.password(), candidate.getPassword());

    if (!passwordsMatches) throw new AuthenticationException("Username/Password incorrect");

    try {
      return jwtCandidateProvider.createCandidateToken(candidate.getId().toString());

    } catch (Exception e) {
      System.err.println(e.getMessage());
      return null;
    }
  }
}
