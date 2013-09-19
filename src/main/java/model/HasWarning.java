package model;

import java.util.ArrayList;

public class HasWarning {
	
	private ArrayList<String> warnings = new ArrayList<String>();

	public ArrayList<String> getWarning() {
		return warnings;
	}

	public void addWarning(String warning) {
		warnings.add(warning);
	}
}
