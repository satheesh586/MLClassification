package in.ravindra.ReadingRawData;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by satheesh on 24/4/17.
 */
public class ReaderMapper extends Mapper<Text, Text, Text, NullWritable> {
    public void map(Text key, Text value, Context context)
            throws IOException, InterruptedException {
        /*Text outPutKey = new Text();
        IntWritable outPutValue = new IntWritable();*/
        String rawData = value.toString();
        //Integer lineNumber = Integer.parseInt(key.toString());
        String[] data = rawData.split(",");
        for (int j = 0; j < 294; j++) {
                context.write(new Text("N" + "," + key + "," + j + "," + data[j]), null);
            }
        }
}
