package br.com.eric.vagas.modules.company.useCases;

import br.com.eric.vagas.modules.company.dto.AuthCompanyDTO;
import br.com.eric.vagas.modules.company.entities.CompanyEntity;
import br.com.eric.vagas.modules.company.repositories.CompanyRepository;
import br.com.eric.vagas.providers.JWTProvider;
import com.auth0.jwt.exceptions.JWTCreationException;
import javax.naming.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthCompanyUseCase {

  @Autowired CompanyRepository companyRepository;

  @Autowired PasswordEncoder passwordEncoder;

  @Autowired JWTProvider jwtProvider;

  public void execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
    CompanyEntity company =
        companyRepository
            .findByUsername(authCompanyDTO.username())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    boolean passwordMatches =
        passwordEncoder.matches(authCompanyDTO.password(), company.getPassword());

    if (!passwordMatches) throw new AuthenticationException("Invalid password");

    try {
      jwtProvider.createToken(company.getId().toString());

    } catch (JWTCreationException e) {
      System.err.println(e.getMessage());
    }
  }
}
