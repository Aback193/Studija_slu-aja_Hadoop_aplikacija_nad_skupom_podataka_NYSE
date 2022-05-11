
import java.io.*;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat; 
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat; 
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

// Drajver za Properties i Setup

public class AvgStockVolMonthDriver extends Configured implements Tool{
	 public int run(String args[]) throws IOException, InterruptedException, ClassNotFoundException {
		 if (args.length < 2) {
	    	System.err.println("Usage: wordcount <in> [<in>...] <out>");
	    	System.exit(2);
	    }
		Configuration conf=getConf();
		Job job=Job.getInstance(conf,"NYSE average stock volume per month");
		job.setJarByClass(AvgStockVolMonthDriver.class);
		//job.setMapperClass(AvgStockMapperWithProperties.class);
		job.setMapperClass(AvgStockMapperWithSetUp.class);
		//average of average is not right. so we define new combiner class rather reusing reducer task
		////job.setCombinerClass(AvgStockVolMonthCombiner.class);
		
		job.setReducerClass(AvgStockVolPerMonthReducer.class);
		job.setNumReduceTasks(4);		
		job.setMapOutputKeyClass(TextPair.class);
		job.setMapOutputValueClass(LongPair.class);  		
		job.setOutputKeyClass(TextPair.class);
		job.setOutputValueClass(LongPair.class);
		
		String uri="hdfs://192.168.122.207:10001/"+args[0];
		String uri1="hdfs://192.168.122.207:10001/"+args[1];
		FileSystem fs=FileSystem.get(URI.create(uri),conf);
 		Path path=new Path(uri+"/nyse_201[1-4].*");
		FileStatus[] status=fs.globStatus(path); 
		Path[] paths=FileUtil.stat2Paths(status);
		for(Path p:paths){
			System.out.println(p.toString()); 
			FileInputFormat.addInputPath(job,p);
		}
		job.setInputFormatClass(CombineTextInputFormat.class);
		FileOutputFormat.setOutputPath(job, new Path(uri1));	
		
		return job.waitForCompletion(true) ? 0 : 1;
	} 
	public static void main(String args[])throws Exception{ 
		int exitCode=ToolRunner.run(new AvgStockVolMonthDriver(), args);
		 System.exit(exitCode);	    
	}
}
