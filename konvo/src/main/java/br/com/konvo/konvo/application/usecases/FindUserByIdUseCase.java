package br.com.konvo.konvo.application.usecases;

import br.com.konvo.konvo.domain.model.UserClient;
import br.com.konvo.konvo.domain.repository.UserClientRepository;
import org.springframework.stereotype.Service;

@Service
public class FindUserByIdUseCase {


    private UserClientRepository repository;

    public FindUserByIdUseCase(UserClientRepository repository) {
        this.repository = repository;
    }

    public UserClient execute(Long id) {
        return repository.findById(id);
    }
}
