package br.com.eric.vagas.modules.candidate.dto;

import java.time.LocalDateTime;

public record ProfileCandidateDTO(
    String username, String name, String email, String description, LocalDateTime createdAt) {}
