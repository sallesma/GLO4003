package model;

import java.util.ArrayList;
import java.util.List;

public class HasWarning {
	
	private ArrayList<String> warnings = new ArrayList<String>();

	public ArrayList<String> getWarning() {
		return warnings;
	}

	public void addWarning(String warning) {
		warnings.add(warning);
	}
	
	public void addWarning(List<String> warning) {
		warnings.addAll(warning);
	}
}
