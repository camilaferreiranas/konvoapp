package br.com.konvo.konvo.infrastructure.repository;

import br.com.konvo.konvo.domain.exceptions.StockNotFoundException;
import br.com.konvo.konvo.domain.exceptions.UserNotFoundException;
import br.com.konvo.konvo.domain.exceptions.WalletNotFoundException;
import br.com.konvo.konvo.domain.model.Stock;
import br.com.konvo.konvo.domain.model.UserClient;
import br.com.konvo.konvo.domain.model.Wallet;
import br.com.konvo.konvo.domain.repository.WalletRepository;
import br.com.konvo.konvo.infrastructure.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WalletRepositoryAdapter implements WalletRepository {

    private final WalletJpaRepository repository;
    private final UserClientJpaRepository userClientJpaRepository;
    private final StockJpaRepository stockJpaRepository;

    public WalletRepositoryAdapter(WalletJpaRepository repository,
                                   UserClientJpaRepository userClientJpaRepository,
                                   StockJpaRepository stockJpaRepository) {
        this.repository = repository;
        this.userClientJpaRepository = userClientJpaRepository;
        this.stockJpaRepository = stockJpaRepository;
    }

    @Override
    public Wallet findById(Long id) {
        return repository.findById(id)
                .map(this::toDomain)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));
    }

    @Override
    public List<Wallet> findByUserClientId(Long id) {
        return repository.findByUserClientId(id)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Wallet wallet) {
        repository.save(toEntity(wallet));
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
        wallet.setStocks(stockDomainToEntity(stocks));
        repository.save(wallet);
    }

    private List<StockEntity> stockDomainToEntity(List<Stock> stocks) {
        return stocks.stream()
                .map(stock -> {
                  var stockEntity = new StockEntity();
                  stockEntity.setId(stock.getId());
                  stockEntity.setCode(stock.getCode());
                  stockEntity.setCompany(stock.getCompany());
                  stockEntity.setPrice(stock.getPrice());
                  return stockEntity;
                }).collect(Collectors.toList());

    }

    @Override
    public List<Wallet> findAll() {
        return repository.findAll().stream()
                .map(this::toDomain)
                .toList();
    }


    private UserClientEntity convertUserToEntity(UserClient userClient) {
        return userClientJpaRepository.findByEmail(userClient.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }



    private Wallet toDomain(WalletEntity walletEntity) {
        UserClient userClient = toDomain(walletEntity.getUserClient());
        List<Stock> stocks = convertEntityToStock(walletEntity.getStocks());

        return new Wallet(
                walletEntity.getId(),
                walletEntity.getName(),
                walletEntity.getDescription(),
                walletEntity.getTotal(),
                walletEntity.getStatus(),
                userClient,
                stocks
        );
    }




    private UserClient toDomain(UserClientEntity entity) {
        return new UserClient(
                entity.getId(),
                entity.getName(),
                entity.getUsername(),
                entity.getEmail()
        );
    }

    private WalletEntity toEntity(Wallet wallet) {
        var userClient = userClientJpaRepository.findByEmail(wallet.getUserClient().getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        var walletEntity = new WalletEntity();
        walletEntity.setUserClient(userClient);
        walletEntity.setStatus(wallet.getStatus());
        walletEntity.setName(wallet.getName());
        walletEntity.setTotal(wallet.getTotal() != null ? wallet.getTotal() : BigDecimal.ZERO);
        walletEntity.setDescription(wallet.getDescription());



        return walletEntity;
    }

    private StockEntity convertStockToEntity(Stock stock) {
        return stockJpaRepository.findById(stock.getId())
                .orElseThrow(() -> new StockNotFoundException("Stock not found"));
    }

    private List<Stock> convertEntityToStock(List<StockEntity> stocks) {
        return stocks.stream()
                .map(stockEntity -> {
                    return new Stock(stockEntity.getId(), stockEntity.getCode(), stockEntity.getCompany(), stockEntity.getPrice());
                }).collect(Collectors.toList());
    }
}
