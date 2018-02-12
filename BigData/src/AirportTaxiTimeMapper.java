
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AirportTaxiTimeMapper extends Mapper<Object,Text,Text,IntWritable> {
	
	private static final String NOT_APPLICABLE = "NA";
	private static final String TAXI_IN_COLUMN_NAME = "TaxiIn";
	private static final String TAXI_OUT_COLUMN_NAME = "TaxiOut";
	private static final String ORIGIN_COLUMN_NAME = "Origin";
	private static final String DESTINATION_COLUMN_NAME = "Dest";
	
	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
//		super.map(key, value, context);
		
		String theLine = value.toString();
        String[] columns = theLine.split(",");
        
        String theOrigin = columns[16];
        String taxiOut = columns[20];
        
        String dest = columns[17];
        String taxiIn = columns[19];
       
        
        if(isValidEntry(theOrigin,dest,taxiIn,taxiOut)){
        	
            context.write(new Text(theOrigin), new IntWritable(Integer.parseInt(taxiOut)));
            context.write(new Text(dest), new IntWritable(Integer.parseInt(taxiIn)));
            
        }
        
  }
	
	
	private boolean isValidEntry(String origin, String destination, String taxiIn, String taxiOut) {
		
		if(!origin.equals(NOT_APPLICABLE) && !origin.equals(ORIGIN_COLUMN_NAME) &&
                !destination.equals(NOT_APPLICABLE) && !destination.equals(DESTINATION_COLUMN_NAME) &&
                !taxiIn.equals(NOT_APPLICABLE) && !taxiIn.equals(TAXI_IN_COLUMN_NAME) &&
                !taxiOut.equals(NOT_APPLICABLE) && !taxiOut.equals(TAXI_OUT_COLUMN_NAME)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
}


