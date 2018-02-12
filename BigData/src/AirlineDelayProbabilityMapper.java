
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AirlineDelayProbabilityMapper extends Mapper<Object, Text, Text, IntWritable> {

	private static final int MAX_DELAY_LIMIT=10;
	private static final String YEAR_DEFAULT="Year";
	private static final String NOT_APPLICABLE = "NA";
	private static final String UNIQUE_CARRIER_DEFAULT = "UniqueCarrier";
	private static final String ARRIVAL_DELAY_DEFAULT = "ArrDelay";
	private static final String DEPARTURE_DELAY_DEFAULT = "DepDelay";
	
	
    @Override
	protected void map(Object theKey, Text theValue, Mapper<Object, Text, Text, IntWritable>.Context theContext)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		String line = theValue.toString();
        String[] columns = line.split(","); 
        String theYear = columns[0];
        String theCarrier = columns[8];
        String theDepDelayMinutes = columns[15];
        String theArrDelayMinutes = columns[14];
        
        
        
  	    if(isValidEntry(theYear,theCarrier,theArrDelayMinutes,theDepDelayMinutes)){
  	    	
            if (Integer.parseInt(theArrDelayMinutes) + Integer.parseInt(theDepDelayMinutes) > MAX_DELAY_LIMIT) {
            	theContext.write(new Text(theCarrier), new IntWritable(0));
            } 
            else {
            	theContext.write(new Text(theCarrier), new IntWritable(1));
            }
               
        }
	}
    
    /**
     * Checks if the entry is valid. Returns true if it is and false if its not
     * @param theYear
     * @param theCarrier
     * @param ArrivalDelay
     * @param DepartureDelay
     * @return
     */
    private boolean isValidEntry(String theYear, String theCarrier, String ArrivalDelay, String DepartureDelay) {
    	
    	 if(!theYear.equals(NOT_APPLICABLE) && !theYear.equals(YEAR_DEFAULT) &&
                 !theCarrier.equals(NOT_APPLICABLE) && !theCarrier.equals(UNIQUE_CARRIER_DEFAULT) &&
                 !DepartureDelay.equals(NOT_APPLICABLE) && !DepartureDelay.equals(DEPARTURE_DELAY_DEFAULT) &&
                 !ArrivalDelay.equals(NOT_APPLICABLE) && !ArrivalDelay.equals(ARRIVAL_DELAY_DEFAULT)){
    		 
    		 return true;
    		 
    	 }
    	 else {
    		 return false;
    	 }
    }
		
}


