
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import models.CancelInfo;
import utils.CancelListSort;

public class CancellationCauseReducer extends Reducer<Text,IntWritable,Text,IntWritable> {
	
	private static final String COMMON_CANCELLATION_TITLE = "The most common reason for Cancellation is";
	private static final String NO_DATA_FOUND="No cancellation data found";
	
	private List<CancelInfo> cancellationList=null;
	
	
	@Override
	protected void cleanup(Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		super.cleanup(context);
		if(cancellationList.isEmpty()) {
			context.write(new Text(NO_DATA_FOUND),null);
			return;
		}
		Collections.sort(cancellationList,new CancelListSort());
		
		int lastElement = cancellationList.size()-1;
		context.write(new Text(COMMON_CANCELLATION_TITLE), null);
		
		context.write(new Text(cancellationList.get(lastElement).getCancelCode()), 
					new IntWritable(cancellationList.get(lastElement).getCount()));
		
	}



	@Override
	protected void setup(Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		super.setup(context);
		cancellationList = new ArrayList<>();
	}



	@Override
	protected void reduce(Text arg0, Iterable<IntWritable> arg1,
			Reducer<Text, IntWritable, Text, IntWritable>.Context arg2) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
//		super.reduce(arg0, arg1, arg2);
		
		int theCounter = 0;
		
		for(IntWritable thisValue : arg1) {
			theCounter = theCounter + thisValue.get();
		}
		
		cancellationList.add(new CancelInfo(arg0.toString(),theCounter));
			
	}
	
}
