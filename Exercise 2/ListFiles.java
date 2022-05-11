import org.apache.hadoop.fs.*;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

// listanje fajlova unutar HDFS

public class ListFiles {
	public static void main(String args[])throws Exception{
		String uri="hdfs://192.168.122.207:10001/"+args[0]; // node; list nodes hdfs getconf -confKey fs.defaultFS 
		Configuration conf=new Configuration();
		FileSystem fs=FileSystem.get(URI.create(uri),conf);
		Path path=new Path(uri);
		FileStatus[] status=fs.listStatus(path);  // listanje fajlova i direktorijuma
		Path[] paths=FileUtil.stat2Paths(status);
		for(Path p:paths){
			System.out.println(p.toString());
		}
	}
}
