package br.com.konvo.konvo.application.usecases;

import br.com.konvo.konvo.domain.model.Stock;
import br.com.konvo.konvo.domain.repository.StockRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class FindStockByIdUseCase {

    private StockRepository repository;

    public FindStockByIdUseCase(StockRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "stocks", key = "#id")
    public Stock execute(Long id) {
        return repository.findById(id).map(stock -> new Stock(stock.getId(),
                stock.getCode(), stock.getCompany(), stock.getPrice())).orElseThrow();
    }
}
