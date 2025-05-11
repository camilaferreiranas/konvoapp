package br.com.konvo.konvo.application.usecases;

import br.com.konvo.konvo.domain.model.Wallet;
import br.com.konvo.konvo.domain.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class SaveWalletUseCase {

    private final WalletRepository repository;

    public SaveWalletUseCase(WalletRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void execute(Wallet wallet) {
        repository.save(wallet);
    }
}
