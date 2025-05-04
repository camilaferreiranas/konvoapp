package br.com.konvo.konvo.domain.model;

import br.com.konvo.konvo.infrastructure.persistence.StockEntity;

import java.math.BigDecimal;
import java.util.List;

public class Stock {

    private Long id;
    private String code;
    private String company;
    private BigDecimal price;


    public Stock() {
    }

    public Stock(Long id, String code, String company, BigDecimal price) {
        this.id = id;
        this.code = code;
        this.company = company;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


}
