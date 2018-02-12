
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import models.FlightOnTime;
import utils.FlightListSort;

public class AirlineDelayProbabilityReducer extends Reducer<Text,IntWritable,Text,DoubleWritable> {
	
	List<FlightOnTime> onTimeFlightList=null;
	
	private static final String LOWEST_PROBABILITY_TITLE="Airlines with lowest probability of being on schedule";
	private static final String HIGHEST_PROBABILITY_TITLE= "Airlines with highest probability of being on schedule";

	private static final String NO_DATA_FOUND = "No on schedule data found";
	
	
	@Override
	protected void setup(Reducer<Text, IntWritable, Text, DoubleWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		super.setup(context);
		onTimeFlightList= new ArrayList<>();
	}
	
	
	@Override
	protected void cleanup(Reducer<Text, IntWritable, Text, DoubleWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		super.cleanup(context);
		
		if(onTimeFlightList.isEmpty()) {
			context.write(new Text(NO_DATA_FOUND),null);
			return;
		}
		
		Collections.sort(onTimeFlightList, new FlightListSort());
		
		int lowestCounter = 0;
		int highestCounter = onTimeFlightList.size()-1;
		
		context.write(new Text(LOWEST_PROBABILITY_TITLE), null);
		while(lowestCounter!=3) {
			context.write(new Text(onTimeFlightList.get(lowestCounter).getCarrier()), 
									new DoubleWritable(onTimeFlightList.get(lowestCounter).getOnScheduleProbability()));
			lowestCounter++;
		}
		context.write(new Text(HIGHEST_PROBABILITY_TITLE),null);
		
		while(highestCounter!= onTimeFlightList.size()-4) {
			context.write(new Text(onTimeFlightList.get(highestCounter).getCarrier()),
					new DoubleWritable(onTimeFlightList.get(highestCounter).getOnScheduleProbability()));
			highestCounter --;
		}
		
	}
	
	
	@Override
	protected void reduce(Text theKey, Iterable<IntWritable> theValues,
			Reducer<Text, IntWritable, Text, DoubleWritable>.Context arg2) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		int totalCount = 0;
        int onTimeFlightCounter = 0;
        
        for (IntWritable val : theValues) {
        	 if(val.get() == 1)
             {
               onTimeFlightCounter = onTimeFlightCounter+ 1;
             }
        	 
            totalCount++;
           
        }
       
        onTimeFlightList.add(new FlightOnTime(calculateProbability(onTimeFlightCounter,totalCount),theKey.toString()));
	}
	
	
	
	public double calculateProbability(int onTimeFlightCounter, int totalCount) {
		
		double onTimeFlightCounterInDouble = (double) onTimeFlightCounter;
		double totalCountInDouble = (double) totalCount;
		
		return onTimeFlightCounterInDouble/totalCountInDouble;
		
	}
	

}
