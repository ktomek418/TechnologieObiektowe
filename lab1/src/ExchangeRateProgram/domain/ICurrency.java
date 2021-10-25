package ExchangeRateProgram.domain;

public interface ICurrency {
    int getFactor();

    double getRate();

    String getCode();

    String getName();
}
