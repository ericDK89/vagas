package br.com.eric.vagas.modules.company.controllers;

import br.com.eric.vagas.modules.company.dto.CreateJobDTO;
import br.com.eric.vagas.modules.company.useCases.CreateJobsUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company/job")
public class JobsController {

  @Autowired private CreateJobsUseCase createJobsUseCase;

  @PostMapping
  @PreAuthorize("hasRole('COMPANY')")
  public ResponseEntity<Object> create(
      @Valid @RequestBody CreateJobDTO job, HttpServletRequest request) {
    try {
      Object companyId = request.getAttribute("company_id");
      CreateJobDTO createdJob = createJobsUseCase.execute(job, companyId);
      return ResponseEntity.status(HttpStatus.CREATED).body(createdJob);
    } catch (Exception e) {
      System.err.println(e.getMessage());
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
