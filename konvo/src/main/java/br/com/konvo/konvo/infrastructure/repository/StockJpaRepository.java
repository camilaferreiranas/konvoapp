package br.com.konvo.konvo.infrastructure.repository;

import br.com.konvo.konvo.domain.model.Stock;
import br.com.konvo.konvo.domain.repository.StockRepository;
import br.com.konvo.konvo.infrastructure.persistence.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockJpaRepository extends JpaRepository<StockEntity, Long> {


//    @Override
//    default void save(Stock stock) {
//        save(toEntity(stock));
//    }



    Optional<StockEntity> findByCode(String code);





}
