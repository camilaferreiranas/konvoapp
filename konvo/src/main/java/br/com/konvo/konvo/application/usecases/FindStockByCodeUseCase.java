package br.com.konvo.konvo.application.usecases;

import br.com.konvo.konvo.domain.exceptions.StockNotFoundException;
import br.com.konvo.konvo.domain.model.Stock;
import br.com.konvo.konvo.domain.repository.StockRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class FindStockByCodeUseCase {


    private StockRepository repository;

    public FindStockByCodeUseCase(StockRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "stocks", key = "#code")
    public Stock execute(String code) {
        return repository.findByCode(code).map(
                stock -> new Stock(stock.getId(),
                        stock.getCode(), stock.getCompany(),
                        stock.getPrice())).orElseThrow(() -> new StockNotFoundException("Stock not found"));
    }
}
