package br.com.konvo.konvo.domain.model;

import br.com.konvo.konvo.infrastructure.persistence.WalletEntity;

import java.math.BigDecimal;
import java.util.List;

public class Wallet {


    private Long id;
    private String description;
    private List<Stock> stockList;
    private BigDecimal total;
    private Boolean status;

    public Wallet() {
    }

    public Wallet(Long id, String description, List<Stock> stockList, BigDecimal total, Boolean status) {
        this.id = id;
        this.description = description;
        this.stockList = stockList;
        this.total = total;
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
