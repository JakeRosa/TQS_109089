package bus.booking.services.impl;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import bus.booking.services.CurrencyService;
import lombok.AllArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
@AllArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private JedisPool cache;
    private OkHttpClient client;
    static final Logger log = getLogger(lookup().lookupClass());

    public CurrencyServiceImpl() {
        this.cache = new JedisPool("cache", 6379);
        this.client = new OkHttpClient();
    }

    @Override
    public List<String> getAllCurrencies() {
        Request request = new Request.Builder()
                .url("https://twelve-data1.p.rapidapi.com/forex_pairs?format=json&currency_base=EUR")
                .get()
                .addHeader("X-RapidAPI-Key", "2624a5d66cmsh3cea06db029c24cp10eb3fjsn3024773661fa")
                .addHeader("X-RapidAPI-Host", "twelve-data1.p.rapidapi.com")
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            response.close();

            JSONObject jsonObject = new JSONObject(responseBody);
            JSONArray dataArray = jsonObject.getJSONArray("data");

            List<String> currencyList = new ArrayList<>();

            currencyList.add("EUR");

            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject currencyObject = dataArray.getJSONObject(i);
                String currencySymbol = currencyObject.getString("symbol").split("/")[1];
                currencyList.add(currencySymbol);
            }

            log.info("Currency list fetched successfully");
            return currencyList;

        } catch (IOException e) {
            log.error("API error occurred");
            return new ArrayList<>();
        }
    }

    @Override
    public double convertCurrency(double price, String currency) {
        double rate;

        try (Jedis jedis = cache.getResource()) {
            if (!jedis.exists(currency)) {
                log.info("Currency not found in cache, fetching from API");

                Request request = new Request.Builder()
                        .url("https://twelve-data1.p.rapidapi.com/exchange_rate?symbol=EUR%2F" + currency)
                        .get()
                        .addHeader("X-RapidAPI-Key", "2624a5d66cmsh3cea06db029c24cp10eb3fjsn3024773661fa")
                        .addHeader("X-RapidAPI-Host", "twelve-data1.p.rapidapi.com")
                        .build();

                Response response = client.newCall(request).execute();
                String responseBody = response.body().string();
                response.close();

                JSONObject jsonObject = new JSONObject(responseBody);
                rate = jsonObject.getDouble("rate");

                jedis.setex(currency, 3600, Double.toString(rate));
            } else {
                log.info("Currency found in cache");

                rate = Double.parseDouble(jedis.get(currency));
            }

            return Math.round(price * rate * 100.0) / 100.0;
        } catch (Exception e) {
            log.error("Cache or API error occurred");
            return -1;
        }
    }
}
