package br.com.konvo.konvo.application.usecases;

import br.com.konvo.konvo.domain.model.Stock;
import br.com.konvo.konvo.domain.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveAllStocksUseCase {

    private final StockRepository repository;

    public SaveAllStocksUseCase(StockRepository repository) {
        this.repository = repository;
    }

    public void execute(List<Stock> stocks) {
        repository.saveAll(stocks);
    }
}
