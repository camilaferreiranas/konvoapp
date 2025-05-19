package br.com.konvo.konvo.usecases;

import br.com.konvo.konvo.application.usecases.FindStockByCodeUseCase;
import br.com.konvo.konvo.domain.exceptions.StockNotFoundException;
import br.com.konvo.konvo.domain.model.Stock;
import br.com.konvo.konvo.domain.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class FindStockByCodeUseCaseTest {

    @Mock
    private StockRepository repository;

    @InjectMocks
    private FindStockByCodeUseCase findStockByCodeUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testExecute_shouldCallFindStockByCodeWithValidId() {
        String code = "AAPL";
        when(repository.findByCode(code)).thenReturn(Optional.of(new Stock(1L, "APPL", "Apple Inc.", BigDecimal.valueOf(150.00))));
        findStockByCodeUseCase.execute(code);
        verify(repository, times(1)).findByCode(code);

    }

    @Test
    public void testExecute_shouldCallFindStockByCodeAndThrowStockNotFound() {
        String code = "JAKs";
        when(repository.findByCode(code)).thenReturn(Optional.empty());
        assertThrows(StockNotFoundException.class, () -> findStockByCodeUseCase.execute(code));
    }


}
