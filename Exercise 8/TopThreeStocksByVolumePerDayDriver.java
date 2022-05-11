import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

// 10. Counters Drajver

public class TopThreeStocksByVolumePerDayDriver extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		Configuration conf = getConf();
		
		Job job = Job.getInstance(conf);
		
		job.setJarByClass(getClass());
		
		String uri="hdfs://192.168.122.207:10001/"+args[0];
		String uri1="hdfs://192.168.122.207:10001/"+args[1];
		FileSystem fs=FileSystem.get(URI.create(uri),conf);
 		Path path=new Path(uri+"/nyse_201[1-4].*");
		
		FileStatus[] status = fs.globStatus(path);
		Path[] paths = FileUtil.stat2Paths(status);
		for(Path p : paths) {
			System.out.println(p.toString());
			FileInputFormat.addInputPath(job, p);
		}
		
		job.setInputFormatClass(CombineTextInputFormat.class);
		
		job.setMapperClass(TopThreeStocksByVolumePerDayMapper.class);
		job.setMapOutputKeyClass(LongPairPrimitive.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setPartitionerClass(FirstKeyLongPairPartitioner.class);
		job.setGroupingComparatorClass(LongPairPrimitiveGroupingComparator.class);
		job.setSortComparatorClass(LongPairPrimitiveSortingComparator.class);
		job.setNumReduceTasks(6);
		
		job.setReducerClass(TopThreeStocksByVolumePerDayReducer.class);
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);
		
		FileOutputFormat.setOutputPath(job, new Path(uri1));

		return job.waitForCompletion(true) ? 0 : 1;
	}
	
	public static void main(String[] args) throws Exception {
		System.exit(ToolRunner.run(new TopThreeStocksByVolumePerDayDriver(), args));
	}

}
