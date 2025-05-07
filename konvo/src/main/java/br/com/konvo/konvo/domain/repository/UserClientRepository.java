package br.com.konvo.konvo.domain.repository;

import br.com.konvo.konvo.domain.model.UserClient;

public interface UserClientRepository {

    UserClient findById(Long id);
    UserClient findByEmail(String email);
    void save(UserClient userClient);

}
