package br.com.konvo.konvo.infrastructure.repository;

import br.com.konvo.konvo.domain.model.Stock;
import br.com.konvo.konvo.domain.repository.StockRepository;
import br.com.konvo.konvo.infrastructure.persistence.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockJpaRepository extends JpaRepository<StockEntity, Long>, StockRepository {


    @Override
    default void save(Stock stock) {
        save(toEntity(stock));
    }

    Optional<StockEntity> findByCode(String code);

    static Stock toDomain(StockEntity entity) {
        if (entity == null) {
            return null;
        }
        Stock stock = new Stock();
        stock.setId(entity.getId());
        stock.setCode(entity.getCode());
        stock.setCompany(entity.getCompany());
        stock.setPrice(entity.getPrice());
        // ... outras conversões ...
        return stock;
    }


    static StockEntity toEntity(Stock stock) {
        if (stock == null) {
            return null;
        }
        StockEntity entity = new StockEntity();
        entity.setId(stock.getId());
        entity.setCode(stock.getCode());
        entity.setCompany(stock.getCompany());
        entity.setPrice(stock.getPrice());
        return entity;
    }
}
