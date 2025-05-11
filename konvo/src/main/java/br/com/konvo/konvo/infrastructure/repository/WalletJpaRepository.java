package br.com.konvo.konvo.infrastructure.repository;

import br.com.konvo.konvo.infrastructure.persistence.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WalletJpaRepository extends JpaRepository<WalletEntity, Long> {

    @Query("SELECT w FROM WalletEntity w WHERE w.userClient.id = :userId")
    List<WalletEntity> findByUserClientId(@Param("userId") Long userId);

}
