package br.com.konvo.konvo.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Wallet implements Serializable {


    private Long id;
    private String name;
    private String description;
    private BigDecimal total;
    private Boolean status;
    private UserClient userClient;
    private List<Stock> stocks;

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
                  BigDecimal total,
                  UserClient userClient) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.total = total;
        this.status = true;
        this.userClient = userClient;
    }

    public Wallet(Long id, String name, String description,
                  BigDecimal total, Boolean status,
                  UserClient userClient, List<Stock> stocks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.total = total;
        this.status = status;
        this.userClient = userClient;
        this.stocks = stocks;
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

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }
}
