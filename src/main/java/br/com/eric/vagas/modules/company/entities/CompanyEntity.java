package br.com.eric.vagas.modules.company.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "company")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Length(min = 3, max = 255)
    private String name;

    private String website;

    @Pattern(regexp = "^\\S+$", message = "não pode conter espaço")
    @Length(min = 3, max = 255)
    private String username;

    @Email
    private String email;

    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Length(min = 6, max = 80)
    private String password;
}
