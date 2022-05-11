
import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

// Exercise 1

public class FilterInDriver{
	public static void main(String args[])throws Exception{
		Configuration conf=new Configuration();
		Job job=Job.getInstance(conf,"NYSE average stock volume per month");
		job.setJarByClass(FilterInDriver.class);		
		job.setMapperClass(AvgStockVolPerMonthMapper.class);
		job.setReducerClass(AvgStockVolPerMonthReducer.class);
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
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
