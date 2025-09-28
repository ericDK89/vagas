package br.com.eric.vagas.modules.company.dto;

import br.com.eric.vagas.modules.company.entities.JobsLevels;
import java.util.List;

public record CreateJobDTO(List<String> benefits, JobsLevels level, String description) {}
