
 import java.net.URI;
 import org.apache.hadoop.conf.Configuration;
 import org.apache.hadoop.fs.FileStatus;
 import org.apache.hadoop.fs.FileSystem;
 import org.apache.hadoop.fs.FileUtil;
 import org.apache.hadoop.fs.Path;

//listanje fajlova unutar HDFS sa kustomizacijom
 
 public class  FilePattern  {
 	public static void main(String args[])throws Exception{    
 		String uri="hdfs://192.168.122.207:10001/"+args[0];
 		Configuration conf=new Configuration();	
 		FileSystem fs=FileSystem.get(URI.create(uri),conf);
 		Path path=new Path(uri+"/nyse_201[1-4].*");		// kustomizacija  // args[0] = cmd argument
 		
 		FileStatus[] status=fs.globStatus(path);
 		Path[] paths=FileUtil.stat2Paths(status);
 		for(Path p:paths){
 			System.out.println(p.toString()); 
 		}
 	}
 }
