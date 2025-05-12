package br.com.konvo.konvo.application.usecases;

import br.com.konvo.konvo.domain.model.Stock;
import br.com.konvo.konvo.domain.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyStockUseCase {

    private final WalletRepository repository;

    public BuyStockUseCase(WalletRepository repository) {
        this.repository = repository;
    }

    public void execute(List<Stock> stock, Long id) {
        if(!stock.isEmpty()){
            repository.buyStock(stock, id);
        }
    }
}
