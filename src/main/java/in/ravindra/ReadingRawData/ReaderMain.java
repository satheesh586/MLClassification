package in.ravindra.ReadingRawData;

import in.ravindra.FindingNumberOfPatterns.PatternMapper;
import in.ravindra.FindingNumberOfPatterns.PatternReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by satheesh on 24/4/17.
 */
public class ReaderMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("mapreduce.input.keyvaluelinerecordreader.key.value.separator", " ");
        Job job = new Job(conf, "Forming the matrix from raw data ");
        job.setJarByClass(ReaderMain.class);
        job.setMapperClass(ReaderMapper.class);
        job.setInputFormatClass(KeyValueTextInputFormat.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        job.setNumReduceTasks(0); // Set number of reducers to zero
        FileInputFormat.addInputPath(job, new Path("/home/satheesh/projects/Java/MLClassification/" +
                "src/main/java/in/ravindra/input/input.txt"));
        FileOutputFormat.setOutputPath(job, new Path("/home/satheesh/projects/Java/" +
                "MLClassification/src/main/java/in/ravindra/output/RawData"));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
