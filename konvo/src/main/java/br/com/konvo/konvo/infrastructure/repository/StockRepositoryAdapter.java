package br.com.konvo.konvo.infrastructure.repository;

import br.com.konvo.konvo.domain.model.Stock;
import br.com.konvo.konvo.domain.repository.StockRepository;
import br.com.konvo.konvo.infrastructure.persistence.StockEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class StockJpaRepositoryImpl implements StockRepository {

    private StockJpaRepository stockJpaRepository;

    public StockJpaRepositoryImpl(StockJpaRepository stockJpaRepository) {
        this.stockJpaRepository = stockJpaRepository;
    }

    @Override
    public Optional<Stock> findById(Long id) {
        return stockJpaRepository.findById(id).map(stock -> new Stock(stock.getId(), stock.getCode(), stock.getCompany(), stock.getPrice()));
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

    }

    @Override
    public Optional<Stock> findByCode(String code) {
        return stockJpaRepository.
                findByCode(code).
                map(stock -> new Stock(stock.getId(),
                        stock.getCode(), stock.getCompany(), stock.getPrice()));
    }
}
