package br.com.eric.vagas.modules.candidate.controllers;

import br.com.eric.vagas.modules.candidate.CandidateEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate) {
        System.out.println(candidate.getUsername() + "\n");
        System.out.println(candidate.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body("Success");
    }
}
