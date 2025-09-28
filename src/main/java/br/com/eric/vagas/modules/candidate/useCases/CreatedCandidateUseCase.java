package br.com.eric.vagas.modules.candidate.useCases;

import br.com.eric.vagas.exceptions.UserFoundException;
import br.com.eric.vagas.modules.candidate.entities.CandidateEntity;
import br.com.eric.vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreatedCandidateUseCase {

  @Autowired private CandidateRepository candidateRepository;

  @Autowired private PasswordEncoder passwordEncoder;

  public void execute(CandidateEntity candidateEntity) {
    candidateRepository
        .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
        .ifPresent(
            (user) -> {
              throw new UserFoundException();
            });

    String cryptedPassword = passwordEncoder.encode(candidateEntity.getPassword());
    candidateEntity.setPassword(cryptedPassword);
    candidateRepository.save(candidateEntity);
  }
}
