package br.com.konvo.konvo.usecases;

import br.com.konvo.konvo.application.usecases.FindAllStockUseCase;
import br.com.konvo.konvo.domain.model.Stock;
import br.com.konvo.konvo.domain.repository.StockRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;


public class FindAllStockUseCaseTest {

    @Mock
    private StockRepository repository;


    @InjectMocks
    private FindAllStockUseCase findAllStockUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_shouldCallExecuteStocks() {
        Stock apple = new Stock(1L, "AAPL", "Apple Inc", BigDecimal.valueOf(150.00));
        List<Stock> stocks = Arrays.asList(apple);
        when(repository.findAll()).thenReturn(stocks);
        List<Stock> result = findAllStockUseCase.execute();
        Assertions.assertEquals(stocks.size(), result.size());
    }

}
