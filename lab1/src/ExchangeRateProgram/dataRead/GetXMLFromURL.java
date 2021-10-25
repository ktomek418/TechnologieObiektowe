package ExchangeRateProgram.dataRead;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.net.URLConnection;

public class GetXMLFromURL implements IGetXMLFile {
    private final String link;

    public GetXMLFromURL(String link){
        this.link = link;
    }


    @Override
    public Document getXMLFile(){
        try{
            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(conn.getInputStream());
            return doc;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;

    }
}
