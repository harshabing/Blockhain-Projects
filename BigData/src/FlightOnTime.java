package models;

public class FlightOnTime {
	
	
	private double onScheduleProbability;
    private String carrier;
    
    public FlightOnTime(double onScheduleProbability, String carrier) 
    {
         this.onScheduleProbability = onScheduleProbability;
         this.carrier = carrier;
    }
    
   
	public double getOnScheduleProbability() {
		return onScheduleProbability;
	}


	public void setOnScheduleProbability(double onScheduleProbability) {
		this.onScheduleProbability = onScheduleProbability;
	}


	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

}
