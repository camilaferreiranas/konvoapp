package br.com.konvo.konvo.application.usecases;

import br.com.konvo.konvo.domain.model.Stock;
import br.com.konvo.konvo.domain.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindAllStockUseCase {

    private StockRepository repository;


    public FindAllStockUseCase(StockRepository repository) {
        this.repository = repository;
    }

    public List<Stock> findAll() {
        return repository.findAll().stream().map(stock ->
                new Stock(
                        stock.getId(), stock.getCode(),
                        stock.getCompany(), stock.getPrice())
        ).collect(Collectors.toList())
                ;
    }
}
