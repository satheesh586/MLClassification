package in.ravindra.FindingNumberOfPatterns;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by satheesh on 24/4/17.
 */
public class PatternReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    IntWritable outputValue = new IntWritable();
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum  = 0;
        for (IntWritable intWritable : values) {
            sum += intWritable.get();
        }
        outputValue.set(sum);
        context.write(key, outputValue);
    }
}
