package bus.booking.services;

import java.util.List;

public interface CurrencyService {
    public List<String> getAllCurrencies();

    public double convertCurrency(double price, String currency);
}
