package in.ravindra.HadoopVersion;

import java.io.*;

/**
 * Created by satheesh on 24/4/17.
 */
public class GenerateLineNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/satheesh/projects/Java/MLClassification/src/main/java/in/ravindra" +
                "/input/change.arff"));
        FileWriter fileWriter = new FileWriter("/home/satheesh/projects/Java/MLClassification/src/main/java/" +
                "in/ravindra/input/input.txt");
        int lineCount = 0;
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            fileWriter.write(lineCount + " " + line + "\n");
            lineCount++;
        }
        fileWriter.close();
        bufferedReader.close();
    }
}
