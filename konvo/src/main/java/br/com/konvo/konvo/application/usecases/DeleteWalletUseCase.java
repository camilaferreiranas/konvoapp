package br.com.konvo.konvo.application.usecases;

import br.com.konvo.konvo.domain.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteWalletUseCase {

    private final WalletRepository repository;

    public DeleteWalletUseCase(WalletRepository repository) {
        this.repository = repository;
    }

    public void execute(Long id) {
        repository.delete(id);
    }
}
