package br.com.konvo.konvo.infrastructure.repository;

import br.com.konvo.konvo.infrastructure.persistence.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockJpaRepository extends JpaRepository<StockEntity, Long> {

    Optional<StockEntity> findByCode(String code);

}
