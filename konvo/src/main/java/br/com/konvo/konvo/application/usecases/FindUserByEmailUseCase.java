package br.com.konvo.konvo.application.usecases;

import br.com.konvo.konvo.domain.model.UserClient;
import br.com.konvo.konvo.domain.repository.UserClientRepository;
import org.springframework.stereotype.Service;

@Service
public class FindUserByEmailUseCase {


    private final UserClientRepository repository;

    public FindUserByEmailUseCase(UserClientRepository repository) {
        this.repository = repository;
    }

    public UserClient execute(String email) {
        return repository.findByEmail(email);
    }
}
