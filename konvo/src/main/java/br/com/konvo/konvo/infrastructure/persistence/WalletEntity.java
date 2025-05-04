package br.com.konvo.konvo.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class WalletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @OneToMany
    private List<StockEntity> stocks;

    private BigDecimal total;

    private Boolean status;
}
