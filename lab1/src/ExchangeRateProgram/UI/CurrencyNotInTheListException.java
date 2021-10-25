package ExchangeRateProgram.UI;

public class CurrencyNotInTheListException extends Exception{

    public CurrencyNotInTheListException(String errorMessage) {
        super(errorMessage);
    }
}
