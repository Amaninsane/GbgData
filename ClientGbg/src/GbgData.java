import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class GbgData {
	private String value;
	private String time;
	
	public void xmlNodes() throws Exception {
		URL url = new URL("http://data.goteborg.se/RiverService/v1.1/Measurements/c2b4837f-f9e7-40a0-b7ff-429144513424/Landvetter/Level");
		URLConnection connection = url.openConnection();
		Document doc = parseXML(connection.getInputStream());
		NodeList valueNodes = doc.getElementsByTagName("Value");
		NodeList timeNodes = doc.getElementsByTagName("TimeStamp");
		for(int i=0; i<valueNodes.getLength();i++)
		{
			value = valueNodes.item(i).getTextContent().toString();
			time = timeNodes.item(i).getTextContent();	
			System.out.println("The height in RH2000: "+value);
			System.out.println("The time: "+time);
		}
	}
	private Document parseXML(InputStream stream)
			throws Exception
	{
		DocumentBuilderFactory objDocumentBuilderFactory = null;
		DocumentBuilder objDocumentBuilder = null;
		Document doc = null;
		try
		{
			objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
			objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

			doc = objDocumentBuilder.parse(stream);
		}
		catch(Exception ex)
		{
			throw ex;
		}       
		return doc;
	}

	public String getValue() {
		return this.value;
	}

	public String getTime() {
		return this.time;
	}


}
