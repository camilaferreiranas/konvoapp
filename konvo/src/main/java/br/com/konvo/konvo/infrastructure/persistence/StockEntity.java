package br.com.konvo.konvo.infrastructure.persistence;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "stock")
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, updatable = false)
    private String code;

    private String company;

    @Digits(integer = 10, fraction = 2)
    private BigDecimal price;


}
