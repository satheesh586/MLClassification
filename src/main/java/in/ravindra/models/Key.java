package in.ravindra.models;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by satheesh on 24/4/17.
 */
public class Key implements WritableComparable {

    private IntWritable classId;
    private IntWritable attrId;

    public void write(DataOutput dataOutput) throws IOException {

    }

    public void readFields(DataInput dataInput) throws IOException {
        classId.readFields(dataInput);
        attrId.readFields(dataInput);
    }

    public int compareTo(Object other) {
        Key o = (Key) other;
        if (classId.compareTo(o.classId)==0) {
            return (attrId.compareTo(o.attrId));
        }
        else return (classId.compareTo(o.attrId));
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Key) {
            Key other = (Key) o;
            return classId.equals(other.classId) && attrId.equals(other.attrId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return classId.hashCode() & attrId.hashCode();
    }

}
