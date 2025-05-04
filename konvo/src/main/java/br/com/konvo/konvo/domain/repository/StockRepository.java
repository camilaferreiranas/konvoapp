package br.com.konvo.konvo.domain.repository;

import br.com.konvo.konvo.domain.model.Stock;
import br.com.konvo.konvo.infrastructure.persistence.StockEntity;

import java.util.List;
import java.util.Optional;

public interface StockRepository {

    Optional<StockEntity> findById(Long id);
    List<StockEntity> findAll();
    void save(Stock stock);
    Optional<StockEntity> findByCode(String code);
}
