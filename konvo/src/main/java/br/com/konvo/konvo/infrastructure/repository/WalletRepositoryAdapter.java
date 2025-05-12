package br.com.konvo.konvo.infrastructure.repository;

import br.com.konvo.konvo.domain.exceptions.WalletNotFoundException;
import br.com.konvo.konvo.domain.model.Stock;
import br.com.konvo.konvo.domain.model.Wallet;
import br.com.konvo.konvo.domain.repository.WalletRepository;
import br.com.konvo.konvo.infrastructure.mapper.WalletMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WalletRepositoryAdapter implements WalletRepository {

    private final WalletJpaRepository repository;
    private final WalletMapper walletMapper;

    public WalletRepositoryAdapter(WalletJpaRepository repository,
                                   WalletMapper walletMapper) {
        this.repository = repository;
        this.walletMapper = walletMapper;
    }

    @Override
    public Wallet findById(Long id) {
        return repository.findById(id)
                .map(walletMapper::toDomain)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));
    }

    @Override
    public List<Wallet> findByUserClientId(Long id) {
        return repository.findByUserClientId(id)
                .stream()
                .map(walletMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Wallet wallet) {
        repository.save(walletMapper.toEntity(wallet));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        var wallet = repository.findById(id).orElseThrow(() ->
                new WalletNotFoundException("Wallet not found"));
        wallet.setStatus(false);
        repository.save(wallet);
    }

    @Override
    public void buyStock(List<Stock> stocks, Long id) {
        var wallet = repository.findById(id).orElseThrow(() ->
                new WalletNotFoundException("Wallet not found"));
        wallet.setStocks(walletMapper.toEntityList(stocks));
        repository.save(wallet);
    }

    @Override
    public List<Wallet> findAll() {
        return repository.findAll().stream()
                .map(walletMapper::toDomain)
                .toList();
    }
}
