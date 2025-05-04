package br.com.konvo.konvo.infrastructure.repository;

import br.com.konvo.konvo.infrastructure.persistence.UserClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserClientJpaRepository extends JpaRepository<UserClientEntity, Long> {
}
