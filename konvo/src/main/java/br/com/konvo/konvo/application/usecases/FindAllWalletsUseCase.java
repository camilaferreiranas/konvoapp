package br.com.konvo.konvo.application.usecases;

import br.com.konvo.konvo.domain.model.Wallet;
import br.com.konvo.konvo.domain.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllWalletsUseCase {

    private final WalletRepository repository;

    public FindAllWalletsUseCase(WalletRepository repository) {
        this.repository = repository;
    }

    public List<Wallet> execute() {
        return repository.findAll();
    }
}
