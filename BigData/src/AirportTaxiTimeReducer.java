
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import models.Airport;
import utils.AirportListSort;

public class AirportTaxiTimeReducer extends Reducer<Text,IntWritable,Text,DoubleWritable> {
	
	private static final String HIGHEST_AVERAGE_TITLE="Airports with the Highest average taxi time";
	private static final String LOWEST_AVERAGE_TITLE="Airports with the lowest average taxi time";
	private static final String NO_DATA_FOUND="No taxi data found";
	
	List<Airport> airportsList=null;
	
	
	@Override
	protected void setup(Reducer<Text, IntWritable, Text, DoubleWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		super.setup(context);
		airportsList = new ArrayList<>();
	}
	
	
	@Override
	protected void cleanup(Reducer<Text, IntWritable, Text, DoubleWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		super.cleanup(context);
		
		if(airportsList.isEmpty()) {
			context.write(new Text(NO_DATA_FOUND), null);
			return;
		}
		
		Collections.sort(airportsList, new AirportListSort());
		
		int lowestCounter = 0;
		
		int highestCounter = airportsList.size()-1;
		
		context.write(new Text(LOWEST_AVERAGE_TITLE), null);
		
		while(lowestCounter!=3) {
			context.write(new Text(airportsList.get(lowestCounter).getAirportName()), 
									new DoubleWritable(airportsList.get(lowestCounter).getAverageTaxiTime()));
			lowestCounter++;
		}
		
		context.write(new Text(HIGHEST_AVERAGE_TITLE),null);
		
		while(highestCounter!= airportsList.size()-4) {
			context.write(new Text(airportsList.get(highestCounter).getAirportName()),
					new DoubleWritable(airportsList.get(highestCounter).getAverageTaxiTime()));
			highestCounter --;
		}
		
	}
	
	@Override
	protected void reduce(Text theKey, Iterable<IntWritable> theMappedValues,
			Reducer<Text, IntWritable, Text, DoubleWritable>.Context arg2) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
//		super.reduce(theKey, theMappedValues, arg2);
		
		int totalTaxiTime = 0;
		int totalTimesTaxied = 0;
		
		for(IntWritable value: theMappedValues) {
			
			totalTaxiTime = totalTaxiTime + value.get();
			totalTimesTaxied ++;
		}
		double averageForThisAirport = calculateAverageTime(totalTaxiTime,totalTimesTaxied);
		
		if(averageForThisAirport!=0) {
			airportsList.add(new Airport(theKey.toString(),averageForThisAirport));
		}
		
		
	}
	
	private double calculateAverageTime(int totalTaxiTime,int totalTimesTaxied) {
		
		double totalTimesTaxiedInDouble = (double) totalTimesTaxied;
		double totalTaxiTimeInDouble = (double) totalTaxiTime;
		
		return totalTaxiTimeInDouble/totalTimesTaxiedInDouble;
		
	}
	
	
	
	
	
}
