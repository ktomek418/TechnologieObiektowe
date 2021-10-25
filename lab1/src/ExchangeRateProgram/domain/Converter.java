package ExchangeRateProgram.domain;


public abstract class Converter {

    public static double convert(ICurrency c1, ICurrency c2, double value){
        return Math.round((c1.getRate()/c1.getFactor() * value)/(c2.getRate()/c2.getFactor()) * 100.0)/100.0;
    }
}
