package in.ravindra.HadoopVersion;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Created by satheesh on 24/4/17.
 */
public class MLClassifierReducer extends Reducer<Text, IntWritable, Text, NullWritable> {
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        double sum  = 0;
        for (IntWritable intWritable : values) {
            sum += intWritable.get();
        }
        sum = step3(key, sum, context);
        String value = key.toString();
        String[] data = value.split(" ");
        context.write(new Text("M" + "," + data[0] + ","+  data[1] + "," + sum * 100), null);
    }

    private double step3(Text key, double sum, Context context) {
        String value = key.toString();
        String[] data = value.split(" ");
        if (data[0].equals("0")) {
            return sum/Double.parseDouble(context.getConfiguration().get("class1"));
        }
        if (data[0].equals("1")) {
            return sum/Double.parseDouble(context.getConfiguration().get("class2"));
        }
        if (data[0].equals("2")) {
            return sum/Double.parseDouble(context.getConfiguration().get("class3"));
        }
        if (data[0].equals("3")) {
            return sum/Double.parseDouble(context.getConfiguration().get("class4"));
        }
        if (data[0].equals("4")) {
            return sum/Double.parseDouble(context.getConfiguration().get("class5"));
        }
        if (data[0].equals("5")) {
            return sum/Double.parseDouble(context.getConfiguration().get("class6"));
        }
        return 0;
    }
}
