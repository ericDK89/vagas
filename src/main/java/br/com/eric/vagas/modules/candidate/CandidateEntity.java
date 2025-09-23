package br.com.eric.vagas.modules.candidate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CandidateEntity {
    private UUID id = UUID.randomUUID();

    @NotEmpty
    @Length(min = 3, max = 80)
    private String name;

    @Pattern(regexp = "^\\S+$", message = "não pode conter espaço")
    @Length(min = 3, max = 80)
    private String username;

    @Email
    private String email;

    @Length(min = 8, max = 80)
    private String password;

    @NotEmpty
    private String description;
    private String curriculum;
    private LocalDate createdAt = LocalDate.now();
    private LocalDate updatedAt;
}
