package br.com.eric.vagas.modules.candidate.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotEmpty
    @Length(min = 3, max = 80)
    private String name;

    @Pattern(regexp = "^\\S+$", message = "não pode conter espaço")
    @Length(min = 3, max = 80)
    @NotEmpty
    private String username;

    @Email
    @NotEmpty
    private String email;

    @Length(min = 8, max = 80)
    @NotEmpty
    private String password;

    @NotEmpty
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
