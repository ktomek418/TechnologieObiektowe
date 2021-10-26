import ExchangeRateProgram.UI.UserInterface;
import ExchangeRateProgram.dataRead.DataProvider;
import ExchangeRateProgram.dataRead.GetDataFromXML;
import ExchangeRateProgram.dataRead.GetXMLFromURL;
import ExchangeRateProgram.dataRead.IGetXMLFile;
import ExchangeRateProgram.domain.CurrencyCollection;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        IGetXMLFile xmlProvider = new GetXMLFromURL("https://www.nbp.pl/kursy/xml/lasta.xml");
        DataProvider dataProvider = new GetDataFromXML(xmlProvider);
        CurrencyCollection collection = CurrencyCollection.getInstance();
        collection.createCollection(dataProvider);
        Scanner scanner = new Scanner(System.in);
        UserInterface UI = new UserInterface(scanner, collection);
        UI.program();
    }
}
