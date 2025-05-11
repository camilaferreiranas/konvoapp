package br.com.konvo.konvo.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class StockPosition implements Serializable {

    private Long id;
    private UserClient owner;
    private Wallet wallet;
    private Stock stock;
    private BigDecimal totalInvested;

    public StockPosition() {
    }

    public StockPosition(Long id, UserClient owner, Wallet wallet,
                         Stock stock, BigDecimal totalInvested) {
        this.id = id;
        this.owner = owner;
        this.wallet = wallet;
        this.stock = stock;
        this.totalInvested = totalInvested;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserClient getOwner() {
        return owner;
    }

    public void setOwner(UserClient owner) {
        this.owner = owner;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public BigDecimal getTotalInvested() {
        return totalInvested;
    }

    public void setTotalInvested(BigDecimal totalInvested) {
        this.totalInvested = totalInvested;
    }
}
