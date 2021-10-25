package ExchangeRateProgram.domain;

public class Currency implements ICurrency {

    private final int factor;
    private final double rate;
    private final String code;
    private final String name;

    public Currency(int factor, double rate, String code, String name){
        this.factor = factor;
        this.rate = rate;
        this.code = code;
        this.name = name;
    }

    @Override
    public int getFactor() {
        return factor;
    }

    @Override
    public double getRate() {
        return rate;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

}
