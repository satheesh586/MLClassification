package in.ravindra.HadoopVersion;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by satheesh on 24/4/17.
 */
public class MakeMatrix {
    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter("/home/satheesh/projects/Java/MLClassification/" +
                "src/main/resources/initilaMatrix.txt");
        int np  = 294, q = 2408;
        for (int i = 0;i < q; i++) {
            for (int j = 0; j < np; j++) {
                fileWriter.write("M " + i + " " + j + " " + 0 + "\n");
            }
        }
    }
}
