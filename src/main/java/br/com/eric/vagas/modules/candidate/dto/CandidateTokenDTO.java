package br.com.eric.vagas.modules.candidate.dto;


import java.time.LocalDateTime;

public record CandidateTokenDTO(String token, LocalDateTime expiresIn) {}
