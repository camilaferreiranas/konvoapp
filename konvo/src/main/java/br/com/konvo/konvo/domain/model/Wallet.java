package br.com.konvo.konvo.domain.model;

import br.com.konvo.konvo.infrastructure.persistence.WalletEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Wallet implements Serializable {


    private Long id;
    private String name;
    private String description;
    private List<StockPosition> stockList;
    private BigDecimal total;
    private Boolean status;

    public Wallet() {
    }

    public Wallet(Long id, String name, String description, BigDecimal total, Boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.total = total;
        this.status = status;
    }


    public Wallet(Long id, String name, String description, List<StockPosition> stockList, BigDecimal total, Boolean status) {
        this.id = id;
        this.name = name;
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StockPosition> getStockList() {
        return stockList;
    }

    public void setStockList(List<StockPosition> stockList) {
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
