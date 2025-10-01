package br.com.eric.vagas.modules.candidate.controllers;

import br.com.eric.vagas.modules.candidate.dto.AuthRequestCandidateDTO;
import br.com.eric.vagas.modules.candidate.dto.CandidateTokenDTO;
import br.com.eric.vagas.modules.candidate.useCases.AuthCandidateUseCase;
import javax.naming.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class AuthCandidateController {

  @Autowired AuthCandidateUseCase authCandidateUseCase;

  @PostMapping("/auth")
  public ResponseEntity<Object> auth(@RequestBody AuthRequestCandidateDTO authRequestCandidateDTO)
      throws AuthenticationException {
    try {
      CandidateTokenDTO candidateTokenDTO = authCandidateUseCase.execute(authRequestCandidateDTO);
      return ResponseEntity.ok().body(candidateTokenDTO);

    } catch (UsernameNotFoundException e) {
      System.err.println(e.getMessage());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());

    } catch (AuthenticationException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());

    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
