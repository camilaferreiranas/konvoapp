package br.com.konvo.konvo.domain.repository;

import br.com.konvo.konvo.domain.model.Stock;
import br.com.konvo.konvo.infrastructure.persistence.StockEntity;

import java.util.List;
import java.util.Optional;

public interface StockRepository {

    Optional<Stock> findById(Long id);
    List<Stock> findAll();
    void save(Stock stock);
    Optional<Stock> findByCode(String code);
    void saveAll(List<Stock> stocks);
}
