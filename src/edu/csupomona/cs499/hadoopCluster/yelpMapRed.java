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
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.*;

import com.google.gson.*;

public class yelpMapRed {

	public static class Map extends
			Mapper<Text, LongWritable, Text, LongWritable> {
		public void map(Object key, Text value, Context context) {
			JSONParser parse = new JSONParser();
			try {
				JSONArray obj = (JSONArray) parse.parse(new FileReader(
						"yelp_academic_dataset.json"));
				for (Object o : obj) {
					JSONObject ob = (JSONObject) o;
					String type = (String) ob.get("type");
					if (type == "business"){
						JSONArray ar = (JSONArray) ob.get("categories");
						boolean food = false;
						for (Object c : ar){
							if (c=="Restaurants"||c=="Food"){
								food = true;
								break;
							}
						}
						if(food){
							JSONArray schools = (JSONArray) ob.get("schools");
							LongWritable rating = (LongWritable) ob.get("stars");
							for (Object s : schools){
								Text name = (Text) s;
								try {
									context.write(name,rating);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {

	}

}
