package in.ravindra.FindingNumberOfPatterns;

import in.ravindra.HadoopVersion.MLClassifier;
import in.ravindra.HadoopVersion.MLClassifierMapper;
import in.ravindra.HadoopVersion.MLClassifierReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by satheesh on 24/4/17.
 */
public class Pattern {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        //conf.set("mapreduce.input.keyvaluelinerecordreader.key.value.separator", " ");
        Job job = new Job(conf, "Countint the Number patterns ");
        job.setJarByClass(Pattern.class);
        job.setMapperClass(PatternMapper.class);
        job.setReducerClass(PatternReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        job.setNumReduceTasks(1); // Set number of reducers to zero
        FileInputFormat.addInputPath(job, new Path("/home/satheesh/projects/Java/MLClassification/" +
                "src/main/java/in/ravindra/input/change.arff"));
        FileOutputFormat.setOutputPath(job, new Path("/home/satheesh/projects/Java/" +
                "MLClassification/src/main/java/in/ravindra/output/pattern"));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
