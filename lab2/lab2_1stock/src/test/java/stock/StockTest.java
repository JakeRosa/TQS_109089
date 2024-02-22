package stock;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StockTest {

    @Mock
    IStockmarketService mockedStockService;

    @InjectMocks
    StocksPortfolio portfolio;

    @Test
    void calTotalValueTest() {
        when(mockedStockService.lookUpPrice("AMZN")).thenReturn(3.5);
        when(mockedStockService.lookUpPrice("EBAY")).thenReturn(2.0);

        portfolio.addStock(new Stock("AMZN", 2));
        portfolio.addStock(new Stock("EBAY", 5));

        // assert using JUnit
        assertEquals(17.0, portfolio.totalValue());
        verify(mockedStockService).lookUpPrice(anyString());

        // assert using Hamcrest
        assertThat(portfolio.totalValue(), equalTo(17.0));
    }
}
