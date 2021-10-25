package ExchangeRateProgram.dataRead;

import ExchangeRateProgram.domain.ICurrency;

import java.util.ArrayList;

public  abstract class DataProvider {

    public abstract ArrayList<ICurrency> getData();
}
