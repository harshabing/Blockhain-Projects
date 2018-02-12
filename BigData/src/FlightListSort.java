package utils;

import java.util.Comparator;

import models.FlightOnTime;

public class FlightListSort implements Comparator<FlightOnTime> {

	@Override
	public int compare(FlightOnTime flight1, FlightOnTime flight2) {
		// TODO Auto-generated method stub
		double flight1prob = flight1.getOnScheduleProbability();
		double flight2prob = flight2.getOnScheduleProbability();
		if( flight1prob > flight2prob)
            return 1;
          else if( flight1prob < flight2prob)
            return -1;
          else 
            return 0;
	}

}
