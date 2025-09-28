package br.com.eric.vagas.modules.company.useCases;

import br.com.eric.vagas.exceptions.UserFoundException;
import br.com.eric.vagas.modules.company.entities.CompanyEntity;
import br.com.eric.vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

  @Autowired private CompanyRepository companyRepository;

  @Autowired private PasswordEncoder passwordEncoder;

  public void execute(CompanyEntity companyEntity) {
    companyRepository
        .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
        .ifPresent(
            (user) -> {
              throw new UserFoundException();
            });

    String cryptedPassword = passwordEncoder.encode(companyEntity.getPassword());
    companyEntity.setPassword(cryptedPassword);
    companyRepository.save(companyEntity);
  }
}
