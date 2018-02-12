package utils;

import java.util.Comparator;

import models.Airport;

public class AirportListSort implements Comparator<Airport> {

	@Override
	public int compare(Airport airport1, Airport airport2) {
		double airport1AvgTime = airport1.getAverageTaxiTime();
		double airport2AvgTime = airport2.getAverageTaxiTime();
		
		 if( airport1AvgTime > airport2AvgTime )
             return 1;
           else if( airport1AvgTime < airport2AvgTime  )
             return -1;
           else
             return 0;
	}

}
