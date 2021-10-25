package ExchangeRateProgram.dataRead;

import ExchangeRateProgram.domain.Currency;
import ExchangeRateProgram.domain.ICurrency;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class GetDataFromXML extends DataProvider {

    private final IGetXMLFile provider;

    public GetDataFromXML(IGetXMLFile provider){
        this.provider = provider;
    }

    @Override
    public ArrayList<ICurrency> getData(){
        Document doc = provider.getXMLFile();
        NodeList nodeList = doc.getElementsByTagName("pozycja");
        ArrayList<ICurrency> currencies = new ArrayList<>();
        for(int i=0; i< nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) node;
                String name = element.getElementsByTagName("nazwa_waluty").item(0).getTextContent();
                int factor = Integer.parseInt(element.getElementsByTagName("przelicznik").item(0).getTextContent());
                String code = element.getElementsByTagName("kod_waluty").item(0).getTextContent();
                double rate = Double.parseDouble(element.getElementsByTagName("kurs_sredni").item(0).getTextContent().replace(",", "."));
                currencies.add(new Currency(factor, rate, code, name));
            }
        }
        currencies.add(new Currency(1, 1.0, "PLN", "złoty polski"));
        return currencies;
    }
}
