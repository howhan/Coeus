package files;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

public class Csv {
	public String filename;
	
	public Csv(String filename) {
		this.filename = filename;		
	}
	
	
	//CSVReader reader=new CSVReader(
			
	public <T> ArrayList<String> ReadFileToTable2 (ObservableList<TableColumn<T,?>> tableColumns) {
		
		try {
			FileInputStream fs = new FileInputStream(filename);
			InputStreamReader reader = new InputStreamReader(fs, StandardCharsets.UTF_16LE);
			int line = reader.read();
			
			
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
//		    new InputStreamReader(new FileInputStream("d:\\a.csv"), "UTF-8"), 
//		    ',', '\'', 1);
//		String[] line;
//		while ((line = reader.readNext()) != null) {
//		    StringBuilder stb = new StringBuilder(400);
//		    for (int i = 0; i < line.length; i++) {
//		         stb.append(line[i]);
//		         stb.append(";");
//		    }
//		    System.out.println(stb);
//		}
//	
	
	/*
	 * This function only returns the data.
	 */
	public <T> ArrayList<String> ReadFileToTable (ObservableList<TableColumn<T,?>> tableColumns) {

        try {
            BufferedReader br = new BufferedReader( new FileReader(filename));
            String strLine = null;
            StringTokenizer st = null;
            int lineNumber = 0, tokenNumber = 0;
            ArrayList<String> res = new ArrayList<String>();
   
            
            //the first line are the column headers, best verified to match tableColumn
            strLine = br.readLine();
            List<String> columns = new ArrayList<String>(Arrays.asList(strLine.split("\t")));
            
            while((strLine = br.readLine()) != null) {
            	lineNumber++;
            	String tString = "";                
                List<String> result = new ArrayList<String>(Arrays.asList(strLine.split("\t")));
                
                if (result.size() == tableColumns.size()) {
                    for (int x=0; x<columns.size(); x++) {
                   	 	tString += tableColumns.get(x).getText() + ":" + result.get(x) + ";";
                    }
                }
                res.add(tString);        
            }
            return res;
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return null;
		
	}
}
