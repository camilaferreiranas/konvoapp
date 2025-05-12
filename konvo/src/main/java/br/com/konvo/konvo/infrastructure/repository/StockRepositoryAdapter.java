package br.com.konvo.konvo.infrastructure.repository;

import br.com.konvo.konvo.domain.model.Stock;
import br.com.konvo.konvo.domain.repository.StockRepository;
import br.com.konvo.konvo.infrastructure.mapper.WalletMapper;
import br.com.konvo.konvo.infrastructure.persistence.StockEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class StockRepositoryAdapter implements StockRepository {

    private final StockJpaRepository stockJpaRepository;
    private final WalletMapper walletMapper;

    public StockRepositoryAdapter(StockJpaRepository stockJpaRepository, WalletMapper walletMapper) {
        this.stockJpaRepository = stockJpaRepository;
        this.walletMapper = walletMapper;
    }

    @Override
    public Optional<Stock> findById(Long id) {
        return stockJpaRepository.findById(id).map(stock ->
                new Stock(stock.getId(), stock.getCode(),
                        stock.getCompany(), stock.getPrice()));
    }

    @Override
    public List<Stock> findAll() {
        return stockJpaRepository.findAll().
                stream().map(stock ->
                        new Stock(stock.getId(), stock.getCode(),
                                stock.getCompany(), stock.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public void save(Stock stock) {
        StockEntity stockEntity = walletMapper.toEntity(stock);
        stockJpaRepository.save(stockEntity);
    }



    @Override
    public Optional<Stock> findByCode(String code) {
        return stockJpaRepository.
                findByCode(code).
                map(stock -> new Stock(stock.getId(),
                        stock.getCode(), stock.getCompany(), stock.getPrice()));
    }

    @Override
    public void saveAll(List<Stock> stocks) {
        List<StockEntity> stockEntities = stocks.stream().map(walletMapper::toEntity).toList();
        stockJpaRepository.saveAll(stockEntities);
    }


}
