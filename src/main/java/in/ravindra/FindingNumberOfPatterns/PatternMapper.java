package in.ravindra.FindingNumberOfPatterns;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by satheesh on 24/4/17.
 */
public class PatternMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        /*Text outPutKey = new Text();
        IntWritable outPutValue = new IntWritable();*/
        String rawData = value.toString();
        //Integer lineNumber = Integer.parseInt(key.toString());
        String[] data = rawData.split(",");
        int length = data.length - 1;
        for (int i = 0; i < 6; i++) {
            if (data[length-i].equals("1")) {
                context.write(new Text(String.valueOf(i)), new IntWritable(1));
            }
        }
    }
}
