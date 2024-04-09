package bus.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import bus.booking.services.impl.CacheStatsServiceImpl;
import bus.booking.services.impl.CurrencyServiceImpl;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CurrencyServiceTest {

    @Mock
    private JedisPool cache;

    @Mock
    private OkHttpClient client;

    @Mock
    private Call call;

    @Mock
    private Response response;

    @Mock
    private ResponseBody responseBody;

    @Mock
    private Jedis jedis;

    @Mock
    private CacheStatsServiceImpl cacheStatsService;

    @InjectMocks
    private CurrencyServiceImpl currencyService;

    @Test
    void testGetAllCurrenciesSuccessfully() throws IOException {
        when(responseBody.string()).thenReturn("{\"data\": [{\"symbol\": \"EUR/USD\"}]}");
        when(response.body()).thenReturn(responseBody);
        when(call.execute()).thenReturn(response);
        when(client.newCall(any(Request.class))).thenReturn(call);

        List<String> result = currencyService.getAllCurrencies();
        assertEquals(Arrays.asList("EUR", "USD"), result);
    }

    @Test
    void testGetAllCurrenciesFailure() throws IOException {
        when(client.newCall(any(Request.class))).thenReturn(call);
        when(call.execute()).thenThrow(new IOException());

        List<String> result = currencyService.getAllCurrencies();
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testConvertCurrencyWithoutCacheSuccessfully() throws IOException {
        String currency = "USD";
        double price = 100;
        double rate = 1.2;
        double expected = Math.round(price * rate * 100.0) / 100.0;

        when(jedis.exists(currency)).thenReturn(false);
        when(cache.getResource()).thenReturn(jedis);
        when(responseBody.string()).thenReturn("{\"rate\": 1.2}");
        when(response.body()).thenReturn(responseBody);
        when(call.execute()).thenReturn(response);
        when(client.newCall(any(Request.class))).thenReturn(call);

        double result = currencyService.convertCurrency(price, currency);

        assertEquals(expected, result);

        verify(cacheStatsService, times(1)).miss();
        verify(cacheStatsService, times(1)).put();
    }

    @Test
    void testConvertCurrencyWithAPIConnectionFailure() throws IOException {
        when(jedis.exists(anyString())).thenReturn(false);
        when(cache.getResource()).thenReturn(jedis);
        when(call.execute()).thenThrow(new IOException());
        when(client.newCall(any(Request.class))).thenReturn(call);

        double result = currencyService.convertCurrency(100, "USD");

        assertEquals(-1, result);
    }

    @Test
    void testConvertCurrencyWithInvalidAPIResponse() throws IOException {
        String currency = "USD";
        double price = 100;

        when(jedis.exists(currency)).thenReturn(false);
        when(cache.getResource()).thenReturn(jedis);
        when(responseBody.string()).thenReturn("invalid response");
        when(response.body()).thenReturn(responseBody);
        when(call.execute()).thenReturn(response);
        when(client.newCall(any(Request.class))).thenReturn(call);

        double result = currencyService.convertCurrency(price, currency);

        assertEquals(-1, result);
    }

    @Test
    void testConvertCurrencyWithCacheSuccessfully() {
        String currency = "USD";
        double price = 100;
        double rate = 1.2;
        double expected = Math.round(price * rate * 100.0) / 100.0;

        when(jedis.exists(currency)).thenReturn(true);
        when(jedis.get(currency)).thenReturn(Double.toString(rate));
        when(cache.getResource()).thenReturn(jedis);

        double result = currencyService.convertCurrency(price, currency);

        assertEquals(expected, result);

        verify(cacheStatsService, times(1)).hit();
    }

    @Test
    void testConvertCurrencyWithJedisConnectionFailure() {
        when(jedis.exists(anyString())).thenThrow(new JedisConnectionException("Connection failed"));
        when(cache.getResource()).thenReturn(jedis);

        double result = currencyService.convertCurrency(100, "USD");
        assertEquals(-1, result);
    }
}
