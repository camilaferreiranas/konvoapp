package br.com.konvo.konvo.domain.repository;

import br.com.konvo.konvo.domain.model.Stock;
import br.com.konvo.konvo.domain.model.StockPosition;
import br.com.konvo.konvo.domain.model.Wallet;

import java.util.List;

public interface WalletRepository {

    Wallet findById(Long id);
    List<Wallet> findByUserClientId(Long id);
    void save(Wallet wallet);
    void delete(Long id);
    void buyStock(List<StockPosition> stocks, Long id);
}
