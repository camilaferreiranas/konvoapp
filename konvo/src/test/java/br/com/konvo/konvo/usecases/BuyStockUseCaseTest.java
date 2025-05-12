package br.com.konvo.konvo.usecases;

import br.com.konvo.konvo.application.usecases.BuyStockUseCase;
import br.com.konvo.konvo.domain.model.Stock;
import br.com.konvo.konvo.domain.repository.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class BuyStockUseCaseTest {

    @Mock
    private WalletRepository repository;

    @InjectMocks
    private BuyStockUseCase buyStockUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_shouldCallBuyStockWithCorrectParameters() {
        Stock apple = new Stock(1L, "AAPL", "Apple Inc", BigDecimal.valueOf(150.00));
        Stock google = new Stock(1L, "GOOGL", "Alphabet Inc.", BigDecimal.valueOf(280.00));
        List<Stock> stocks = Arrays.asList(apple, google);
        Long walletId = 1L;
        buyStockUseCase.execute(stocks, walletId);
        verify(repository, times(1)).buyStock(stocks, walletId);
    }

    @Test
    void testExecute_shouldNotCallBuyStockWhenStocksIsEmpty() {
        List<Stock> stocks = Arrays.asList();
        Long walletId = 1L;
        buyStockUseCase.execute(stocks, walletId);
        verify(repository, times(0)).buyStock(stocks, walletId);
    }
}
