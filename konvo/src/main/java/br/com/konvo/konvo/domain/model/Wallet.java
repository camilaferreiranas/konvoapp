package br.com.konvo.konvo.domain.model;

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
    private UserClient userClient;

    public Wallet() {
    }

    public Wallet(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.total = BigDecimal.valueOf(0.0);
        this.status = true;
    }


    public Wallet(Long id, String name, String description,
                  List<StockPosition> stockList, BigDecimal total,
                  UserClient userClient) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stockList = stockList;
        this.total = total;
        this.status = true;
        this.userClient = userClient;
    }

    public Wallet(BigDecimal total,
                  UserClient userClient, String description,
                  String name, Long id) {
        this.total = total;
        this.status = true;
        this.userClient = userClient;
        this.description = description;
        this.name = name;
        this.id = id;
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

    public UserClient getUserClient() {
        return userClient;
    }

    public void setUserClient(UserClient userClient) {
        this.userClient = userClient;
    }
}
