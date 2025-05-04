package br.com.konvo.konvo.application.usecases;

import br.com.konvo.konvo.domain.model.Stock;
import br.com.konvo.konvo.domain.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class SaveStockUseCase {


    private StockRepository repository;


    public SaveStockUseCase(StockRepository repository) {
        this.repository = repository;
    }


    public void execute(Stock stock) {
        repository.save(stock);
    }
}
