package br.com.eric.vagas.modules.candidate.controllers;

import br.com.eric.vagas.modules.candidate.entities.CandidateEntity;
import br.com.eric.vagas.modules.candidate.useCases.CreatedCandidateUseCase;
import br.com.eric.vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

  @Autowired private CreatedCandidateUseCase createdCandidateUseCase;
  @Autowired private ProfileCandidateUseCase profileCandidateUseCase;

  @PostMapping()
  public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate) {
    try {
      createdCandidateUseCase.execute(candidate);
      return ResponseEntity.status(HttpStatus.CREATED).body("Success");
    } catch (Exception e) {
      System.err.println(e.getMessage());
      return ResponseEntity.badRequest().body("Error: " + e.getMessage());
    }
  }

  @GetMapping
  @PreAuthorize("hasRole('CANDIDATE')")
  public ResponseEntity<Object> get(HttpServletRequest httpServletRequest) {
    try {
      Object candidateId = httpServletRequest.getAttribute("candidate_id");
      var profileCandidateDTO =
          profileCandidateUseCase.execute(UUID.fromString(candidateId.toString()));
      return ResponseEntity.ok().body(profileCandidateDTO);
    } catch (Exception e) {
      System.err.println(e.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }
}
