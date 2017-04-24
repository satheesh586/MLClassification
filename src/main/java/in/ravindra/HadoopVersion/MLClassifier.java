package in.ravindra.HadoopVersion;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
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
public class MLClassifier {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("class1" , "862");
        conf.set("class2" , "1066");
        conf.set("class3" , "866");
        conf.set("class4" , "794");
        conf.set("class5" , "728");
        conf.set("class6" , "854");
        Job job = new Job(conf, "Multi Label classification");
        job.setJarByClass(MLClassifier.class);
        job.setMapperClass(MLClassifierMapper.class);
        job.setReducerClass(MLClassifierReducer.class);
        ///job.setInputFormatClass(KeyValueTextInputFormat.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setNumReduceTasks(1); // Set number of reducers to zero
        FileInputFormat.addInputPath(job, new Path("/home/satheesh/projects/Java/MLClassification/" +
                "src/main/java/in/ravindra/input/change.arff"));
        FileOutputFormat.setOutputPath(job, new Path("/home/satheesh/projects/Java/" +
                "MLClassification/src/main/java/in/ravindra/output/matrix"));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
