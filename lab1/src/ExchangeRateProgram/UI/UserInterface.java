package ExchangeRateProgram.UI;


import ExchangeRateProgram.domain.Converter;
import ExchangeRateProgram.domain.CurrencyCollection;
import ExchangeRateProgram.domain.ICurrency;
import ExchangeRateProgram.domain.ICurrencyCollection;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;


    public UserInterface(Scanner scanner){
        this.scanner = scanner;
    }

    public void program(){
        System.out.println("Program for converting currency exchange rates");
        while(true){
            try{
                menu();
                int command = scanner.nextInt();
                scanner.nextLine();
                if(command == 3 ){
                    break;
                }

                if(command == 1){
                    listAllCurrencies();
                } else if(command == 2){
                    convert();
                }
                System.out.println();
            }catch(InputMismatchException e ) {
                System.out.println("Invalid input!");
                scanner.nextLine();
            } catch (CurrencyNotInTheListException | AmountOfCurrencyLessThanZeroException e){
                System.out.println(e.getMessage());
            }


        }
    }

    private void menu(){
        System.out.println("Press number: ");
        System.out.println("[1] - list all currencies");
        System.out.println("[2] - convert currencies");
        System.out.println("[3] - exit the program");
    }

    private void listAllCurrencies(){
        ArrayList<ICurrency> currencies = CurrencyCollection.getInstance().getAllCurrencies();
        currencies.stream().map(e -> e.getCode() + "  " + e.getName())
                .forEach(System.out::println);
    }

    private void convert() throws CurrencyNotInTheListException, AmountOfCurrencyLessThanZeroException {
        ICurrencyCollection currencies = CurrencyCollection.getInstance();

        System.out.println("Input first currency: ");
        String first = scanner.nextLine();
        ICurrency firstCurrency = currencies.getCurrency(first);
        if(firstCurrency == null ){
            throw new CurrencyNotInTheListException("Currency not found!");
        }

        System.out.println("Input second currency: ");
        String second = scanner.nextLine();
        ICurrency secondCurrency = currencies.getCurrency(second);
        if(secondCurrency == null){
            throw new CurrencyNotInTheListException("Currency not found!");
        }

        System.out.println("Input amount of currency to be exchanged: ");
        double value = scanner.nextDouble();
        if(value < 0){
            throw new AmountOfCurrencyLessThanZeroException("Enter correct amount of currency!");
        }

        System.out.println("You will receive: " + Converter.convert(firstCurrency, secondCurrency, value)
                +" "+ secondCurrency.getCode());
    }
}
