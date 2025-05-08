package br.com.konvo.konvo.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "stock_position")
public class StockPositionEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserClientEntity user;

    @ManyToOne
    private WalletEntity wallet;

    @ManyToOne
    private StockEntity stock;

    private BigDecimal totalInvested;

}
