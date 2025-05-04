package br.com.konvo.konvo.infrastructure.persistence;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class UserClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @Column(unique = true)
    @NotBlank
    private String username;

    @NotBlank
    @Column(unique = true)
    @Email
    private String email;

}
