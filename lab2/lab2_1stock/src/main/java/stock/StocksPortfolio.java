package stock;

import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio {
    private IStockmarketService stockmarketService;
    private List<Stock> stocks;

    public StocksPortfolio(IStockmarketService stockmarketService) {
        this.stockmarketService = stockmarketService;
        stocks = new ArrayList<>();
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public double totalValue() {
        int total = 0;

        for (Stock s : stocks) {
            total += s.getQuantity() * stockmarketService.lookUpPrice(s.getLabel());
        }

        return total;
    }
}
