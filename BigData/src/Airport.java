package models;

public class Airport {
	
	private String airportName;
	private double averageTaxiTime;
	
	
	public Airport(String airportName, double averageTaxiTime) {
		super();
		this.airportName = airportName;
		this.averageTaxiTime = averageTaxiTime;
	}


	public String getAirportName() {
		return airportName;
	}


	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}


	public double getAverageTaxiTime() {
		return averageTaxiTime;
	}


	public void setAverageTaxiTime(double averageTaxiTime) {
		this.averageTaxiTime = averageTaxiTime;
	}
	
	
}
