package br.com.konvo.konvo.infrastructure.repository;

import br.com.konvo.konvo.infrastructure.persistence.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletJpaRepository extends JpaRepository<WalletEntity, Long> {
}
