package br.com.konvo.konvo.application.usecases;

import br.com.konvo.konvo.domain.model.UserClient;
import br.com.konvo.konvo.domain.repository.UserClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllUsersUseCase {


    private final UserClientRepository repository;

    public ListAllUsersUseCase(UserClientRepository repository) {
        this.repository = repository;
    }

    public List<UserClient> execute() {
        return repository.findAll();
    }
}
