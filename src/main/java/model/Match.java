package model;

import java.util.Date;

public class Match {
	
	public enum sports {football, basketball, rugby, soccer, volleyball};
	public enum genre {M, F};	
	
	private Date date;
	private String city;
	private String terrain;
	private Integer amountTicketRemaining;
}
