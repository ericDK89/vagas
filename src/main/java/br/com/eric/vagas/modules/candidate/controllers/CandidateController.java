package br.com.eric.vagas.modules.candidate.controllers;

import br.com.eric.vagas.modules.candidate.entities.CandidateEntity;
import br.com.eric.vagas.modules.candidate.useCases.CreatedCandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

  @Autowired private CreatedCandidateUseCase createdCandidateUseCase;

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
}
