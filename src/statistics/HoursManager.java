package statistics;

import java.io.IOException;
import java.util.ArrayList;

import data.HoursCC;
import files.Xsl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HoursManager {

	public HoursManager() {
		
	}
	
	public ObservableList<HoursCC> LoadFile (String filename, ArrayList<String> cols) throws IOException {
		Xsl excel = new Xsl (filename);

		if (!excel.VerifyTableHeader(1, cols)) {
			System.out.print("This is bad!");
		}
				
		ArrayList<String> input = excel.ReadFileToTable(cols);
		if (input == null) {
			return null;
		}
				
		ObservableList<HoursCC> data = FXCollections.observableArrayList();
		for (int i=0; i<input.size(); i++) {
			HoursCC hours = new HoursCC(input.get(i));
			data.add(hours);
		}		
		return data;
		
	}
}
