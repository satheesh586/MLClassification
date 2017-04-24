package in.ravindra.HadoopVersion;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by satheesh on 24/4/17.
 */
public class MLClassifierMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
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
                for (int j = 0; j < 294; j++) {
                    double attrValue = Double.parseDouble(data[j]);
                    if (attrValue > 0) {
                        context.write(new Text(i + " " + j), new IntWritable(1));
                    }
                }
            }
        }
    }
}
