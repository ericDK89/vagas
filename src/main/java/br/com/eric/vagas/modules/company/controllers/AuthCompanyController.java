package br.com.eric.vagas.modules.company.controllers;

import br.com.eric.vagas.modules.company.dto.AuthCompanyDTO;
import br.com.eric.vagas.modules.company.useCases.AuthCompanyUseCase;
import javax.naming.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class AuthCompanyController {

  @Autowired AuthCompanyUseCase authCompanyUseCase;

  @PostMapping("/auth")
  public ResponseEntity<Object> auth(@RequestBody AuthCompanyDTO authCompanyDTO) {
    try {
      var authCompanyResponseDTO = authCompanyUseCase.execute(authCompanyDTO);
      return ResponseEntity.ok().body(authCompanyResponseDTO);
    } catch (AuthenticationException e) {
      System.err.println(e.getMessage());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    } catch (Exception e) {
      System.err.println(e.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }
}
