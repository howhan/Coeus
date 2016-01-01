package files;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Xml {
	private String filename;
	
	public Xml(String filename) {
		this.filename = filename;		
	}
	
	/*
	 * This function only returns the data.
	 */
	public <T> ArrayList<String> ReadFileToTable (ObservableList <TableColumn<T,?>> tableColumns) throws ParserConfigurationException, SAXException, IOException {	
		
		File inputFile = new File(filename);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        
        if (!doc.getDocumentElement().getNodeName().equals("TABLE")) {
        	return null;
        }
        
        NodeList nList = doc.getElementsByTagName("Staff");
        
        ArrayList<String> res = new ArrayList<String>();
        for (int temp = 0; temp < nList.getLength(); temp++) {
        	Node nNode = nList.item(temp);
        	System.out.println("\nCurrent Element :"+ nNode.getNodeName());
        	    
        	Element eElement = (Element) nNode;
        	NodeList colList = eElement.getElementsByTagName("COLUMN");
        	
        	String tString = "";
			for (int i=0; i<colList.getLength(); i++) {
				Element columnElement = (Element)(colList.item(i));
				if (columnElement.getFirstChild() != null) {
					tString += columnElement.getAttribute("name") + ":" + columnElement.getFirstChild().getTextContent();
					if (i<colList.getLength()) {
						tString += ";";
					}
        		}
     							
			}
			System.out.println(tString);
			res.add(tString);
        }
		return res;
	}
	
		
	public <T> boolean WriteTableToFile (TableView<T> table) throws TransformerException {
		ObservableList<TableColumn<T,?>> tableColumns = table.getColumns();
		ObservableList<T> data = table.getItems();
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = null;
			dBuilder = dbFactory.newDocumentBuilder();
		
	        Document doc = dBuilder.newDocument();
			Element root = doc.createElement("TABLE");
			
			doc.appendChild(root);

			for (T row : data) {
				Element rowElement = doc.createElement(row.getClass().getSimpleName());
				List<String> itemList = (List<String>) Arrays.asList(row.toString().split(";"));

				for (int i=0; i<tableColumns.size(); i++) {
					TableColumn<T,?> column = tableColumns.get(i);	
					String textNode = itemList.get(i);
					
					Element columnElement = doc.createElement("COLUMN");
					columnElement.setAttribute("name",column.getText());
					
					if (!textNode.equals("null")) {
						columnElement.appendChild(doc.createTextNode(textNode));
					}
					
					rowElement.appendChild(columnElement);
					root.appendChild(rowElement);
				}
			}
				 
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
	         
	        // write the content into xml file
	        StreamResult result = new StreamResult(new File(filename));
	        transformer.transform(source, result);
	         
	        // Output to console for testing
	        //StreamResult consoleResult = new StreamResult(System.out);
	        //transformer.transform(source, consoleResult);
						
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
		
}
