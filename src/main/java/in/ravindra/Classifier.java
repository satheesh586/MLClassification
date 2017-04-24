package in.ravindra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

/**
 * Created by satheesh on 17/4/17.
 */
public class Classifier {
    private static int[] classCounts = new int[6];
    public static void main(String[] args) throws IOException {
        long startTime = new Date().getTime();
        double m[][] = readData();
        System.out.println(new Date().getTime() - startTime);
    }

    private static double[][] readData() throws IOException {
        FileReader fileReader = new FileReader("/home/satheesh/projects/Java/MLClassification/" +
                "src/main/resources/scene.arff");
        BufferedReader br = new BufferedReader(fileReader);
        double m[][] = new double[6][294];
        double rawData[][] = new double[14443][294];
        int index  = 0;
        String content;
        while ( (content = br.readLine()) != null) {
            parseLineAndStore(content, m,rawData, index++);
        }
        /*for (int i =0; i < 14443 ;i++) {
            for (int j =0; j < 294 ;j++) {
                System.out.print(rawData[i][j] + "  ");
            }
            System.out.println();
        }
        for (int i =0; i < 6 ;i++) {
            System.out.println(classCounts[i]);
        }*/
        divideMatrix(m);
        /*for (int i =0; i < 6 ;i++) {
            for (int j =0; j < 294 ;j++) {
                System.out.print(m[i][j] + "  ");
            }
            System.out.println();
        }*/
        for(int i = 0; i < 14443; i++) {
            step5(m, rawData[i]);
        }
        return m;
    }

    private static void step5(double[][] m, double[] rawDatum) {
        double classes[] = new double[6];
        double sum = 0, max = 0;
        for (int i =0; i < 6; i++) {
            sum = 0;
            for (int j = 0; j< 294; j++) {
                sum += m[i][j] + rawDatum[j];
            }
            if (max < sum) {
                max = sum;
            }
            classes[i] = sum;
        }
        for (int i =0; i < 6; i++) {
            classes[i] /= max;
        }
/*        for (int i =0; i < 6; i++) {
            System.out.print(classes[i] + " ");
        }*/
//        System.out.println();
    }

    private static void divideMatrix(double[][] m) {
        for (int i =0; i < 6 ;i++) {
            for (int j = 0; j < 294; j++) {
                m[i][j] /=classCounts[i];
            }
        }
    }

    private static void parseLineAndStore(String s, double[][] m, double[][] rawData, int index) {
        String[] data = s.split(",");
        int length = data.length - 1;
        for (int j = 0; j < 294; j++) {
            double value = Double.parseDouble(data[j]);
            rawData[index][j] = value;
        }
        for (int i = 0; i < 6; i++) {
            if (data[length-i].equals("1")) {
                classCounts[5-i] += 1;
                for (int j = 0; j < 294; j++) {
                    double value = Double.parseDouble(data[j]);
                    if (value > 0) {
                        m[5 - i][j] += 1;
                    }
                }
            }
        }
    }
}
