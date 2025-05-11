package br.com.konvo.konvo.infrastructure.repository;

import br.com.konvo.konvo.domain.exceptions.StockNotFoundException;
import br.com.konvo.konvo.domain.exceptions.UserNotFoundException;
import br.com.konvo.konvo.domain.exceptions.WalletNotFoundException;
import br.com.konvo.konvo.domain.model.Stock;
import br.com.konvo.konvo.domain.model.StockPosition;
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
    public void buyStock(List<StockPosition> stocks, Long id) {
        var wallet = repository.findById(id).orElseThrow(() ->
                new WalletNotFoundException("Wallet not found"));
        wallet.setPositions(stockPositionEntities(stocks, wallet));
        repository.save(wallet);
    }

    private List<StockPositionEntity> stockPositionEntities(List<StockPosition> stocks, WalletEntity walletEntity) {
        return stocks.stream()
                .map(stockPosition -> {
                    StockPositionEntity entity = new StockPositionEntity();
                    entity.setStock(convertStockToEntity(stockPosition.getStock()));
                    entity.setTotalInvested(stockPosition.getTotalInvested());
                    entity.setWallet(walletEntity);
                    entity.setUser(convertUserToEntity(stockPosition.getOwner()));
                    return entity;
                })
                .collect(Collectors.toList());
    }




    private UserClientEntity convertUserToEntity(UserClient userClient) {
        return userClientJpaRepository.findByEmail(userClient.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }



    private Wallet toDomain(WalletEntity walletEntity) {
        List<StockPosition> stockPositions = walletEntity.getPositions() != null
                ? walletEntity.getPositions().stream()
                .map(this::toDomain)
                .collect(Collectors.toList())
                : List.of();

        UserClient userClient = toDomain(walletEntity.getUserClient());

        return new Wallet(
                walletEntity.getId(),
                walletEntity.getName(),
                walletEntity.getDescription(),
                stockPositions,
                walletEntity.getTotal(),
                userClient
        );
    }

    private StockPosition toDomain(StockPositionEntity entity) {
        StockPosition position = new StockPosition();
        position.setStock(convertStockToDomain(entity.getStock()));
        position.setTotalInvested(entity.getTotalInvested());
        return position;
    }


    private Stock convertStockToDomain(StockEntity entity) {
        return new Stock(
                entity.getId(),
              entity.getCode(),
                entity.getCompany(),
                entity.getPrice()
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

        if (wallet.getStockList() != null && !wallet.getStockList().isEmpty()) {
            List<StockPositionEntity> positionEntities = wallet.getStockList().stream()
                    .map(stockPosition -> {
                        var entity = new StockPositionEntity();
                        entity.setStock(convertStockToEntity(stockPosition.getStock()));
                        entity.setTotalInvested(stockPosition.getTotalInvested());
                        entity.setWallet(walletEntity);
                        return entity;
                    })
                    .collect(Collectors.toList());

            walletEntity.setPositions(positionEntities);
        }

        return walletEntity;
    }

    private StockEntity convertStockToEntity(Stock stock) {
        return stockJpaRepository.findById(stock.getId())
                .orElseThrow(() -> new StockNotFoundException("Stock not found"));
    }
}
