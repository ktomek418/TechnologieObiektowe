package ExchangeRateProgram.domain;



import java.util.ArrayList;

public interface ICurrencyCollection {
    ICurrency getCurrency(String name);
    ArrayList<ICurrency> getAllCurrencies();
}
