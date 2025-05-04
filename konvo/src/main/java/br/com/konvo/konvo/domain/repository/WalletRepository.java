package br.com.konvo.konvo.domain.repository;

import br.com.konvo.konvo.domain.model.Wallet;

public interface WalletRepository {

    Wallet findById(Long id);
    Wallet findByUserClientId(Long id);
    void save(Wallet wallet);
    void delete(Long id);
}
