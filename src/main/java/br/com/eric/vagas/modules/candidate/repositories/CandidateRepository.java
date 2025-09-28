package br.com.eric.vagas.modules.candidate.repositories;

import br.com.eric.vagas.modules.candidate.entities.CandidateEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {

    //@Query("SELECT c FROM CandidateEntity c WHERE c.username = ?1 or c.email = ?2")
    Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);
    Optional<CandidateEntity> findByUsername(String username);
}
