
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CancellationCauseMapper extends Mapper<Object,Text,Text,IntWritable> {

	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
//		super.map(key, value, context);
		
		
        String[] columns = value.toString().split(",");
        String isCancelled = columns[21];
        String cancelReasonCode = columns[22];
        //Emit Cancellation Code if the flight is cancelled 
        if(isValidEntry(isCancelled,cancelReasonCode)){
            context.write(new Text(cancelReasonCode), new IntWritable(1));
        }
		
        
	}
	
	public boolean isValidEntry(String isCancelled, String cancelReasonCode) {
		if(!isCancelled.equals("NA") && !isCancelled.equals("Cancelled") && isCancelled.equals("1") &&
                !cancelReasonCode.equals("NA") && !cancelReasonCode.equals("CancellationCode") && !cancelReasonCode.isEmpty()) {
			return true;
		}else {
			return false;
		}
		
	}
	
	

}
