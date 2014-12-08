package edu.csupomona.cs499.hadoopCluster;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import java.io.*;
import org.json.simple.*;
import java.util.*;

public class yelpMapRed {

	public static class Map extends
			Mapper<LongWritable, Text, Text, IntWritable> {

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
