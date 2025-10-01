package br.com.eric.vagas.modules.candidate.useCases;

import br.com.eric.vagas.modules.candidate.dto.ProfileCandidateDTO;
import br.com.eric.vagas.modules.candidate.repositories.CandidateRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProfileCandidateUseCase {

  @Autowired CandidateRepository candidateRepository;

  public ProfileCandidateDTO execute(UUID companyId) {
    var candidate =
        candidateRepository
            .findById(companyId)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    return new ProfileCandidateDTO(
        candidate.getUsername(),
        candidate.getName(),
        candidate.getEmail(),
        candidate.getDescription(),
        candidate.getCreatedAt());
  }
}
