package br.com.konvo.konvo.infrastructure.repository;

import br.com.konvo.konvo.domain.model.UserClient;
import br.com.konvo.konvo.domain.repository.UserClientRepository;

public class UserClientRepositoryAdapter implements UserClientRepository {

    private UserClientJpaRepository repository;

    public UserClientRepositoryAdapter(UserClientJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserClient findById(Long id) {
        return null;
    }

    @Override
    public UserClient findByEmail(String email) {
        return null;
    }

    @Override
    public void save(UserClient userClient) {

    }
}
