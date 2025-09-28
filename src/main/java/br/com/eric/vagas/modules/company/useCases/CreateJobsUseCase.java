package br.com.eric.vagas.modules.company.useCases;

import br.com.eric.vagas.exceptions.MissingCompanyId;
import br.com.eric.vagas.modules.company.dto.CreateJobDTO;
import br.com.eric.vagas.modules.company.entities.JobEntity;
import br.com.eric.vagas.modules.company.repositories.JobRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobsUseCase {

  @Autowired private JobRepository jobRepository;

  public CreateJobDTO execute(CreateJobDTO job, Object companyId) throws MissingCompanyId {
    if (companyId == null) throw new MissingCompanyId();

    UUID formatedCompanyId = UUID.fromString(companyId.toString());

    JobEntity createdJob =
        JobEntity.builder()
            .benefits(job.benefits())
            .level(job.level())
            .description(job.description())
            .companyId(formatedCompanyId)
            .build();

    jobRepository.save(createdJob);
    return job;
  }
}
