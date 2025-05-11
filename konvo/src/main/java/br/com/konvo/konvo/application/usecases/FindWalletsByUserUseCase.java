package br.com.konvo.konvo.application.usecases;

import br.com.konvo.konvo.domain.model.Wallet;
import br.com.konvo.konvo.domain.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindWalletsByUserUseCase {

    private final WalletRepository walletRepository;

    public FindWalletsByUserUseCase(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public List<Wallet> execute(Long id) {
        return walletRepository.findByUserClientId(id);
    }
}
