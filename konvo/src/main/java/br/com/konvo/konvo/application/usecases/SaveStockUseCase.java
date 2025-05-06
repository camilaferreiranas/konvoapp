package br.com.konvo.konvo.application.usecases;

import br.com.konvo.konvo.domain.model.Stock;
import br.com.konvo.konvo.domain.repository.StockRepository;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SaveStockUseCase {


    private StockRepository repository;


    public SaveStockUseCase(StockRepository repository) {
        this.repository = repository;
    }


    @Cacheable(value = "stocks", key = "#stock.id != null ? #stock.id : 'defaultKey'")
    @Transactional
    public void execute(Stock stock) {
        repository.save(stock);
    }
}
