package ExchangeRateProgram.domain;

import ExchangeRateProgram.dataRead.DataProvider;

import java.util.ArrayList;

public class CurrencyCollection implements ICurrencyCollection {

    private static CurrencyCollection currencyCollection = null;
    private ArrayList<ICurrency> currencies;

    private CurrencyCollection(){
    }

    public static CurrencyCollection getInstance(){
        if(currencyCollection == null){
            currencyCollection = new CurrencyCollection();
        }
        return currencyCollection;
    }

    public void createCollection(DataProvider provider){
        this.currencies = provider.getData();
    }

    @Override
    public ICurrency getCurrency(String name){
        for(ICurrency currency: currencies){
            if(currency.getName().equalsIgnoreCase(name) || currency.getCode().equalsIgnoreCase(name)){
                return currency;
            }
        }
        return null;
    }

    @Override
    public ArrayList<ICurrency> getAllCurrencies() {
        return currencies;
    }
}
