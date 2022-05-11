
import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

// spajanje svih fajlova iz input foldera u jedan fajl proizvoljnog imena
// ne brisu se izvorni fajlovi

public class CopyMerge {
	public static void main(String args[])throws Exception{
		String uri="hdfs://192.168.122.207:10001/"+args[0];
		String uri1="hdfs://192.168.122.207:10001/"+args[1];
		Configuration conf=new Configuration();	
		FileSystem fs=FileSystem.get(URI.create(uri),conf);
		Path srcPath=new Path(uri);		 // ulazni folder
		Path targetPath=new Path(uri1);   // ime za out fajl
		boolean copyMerge=FileUtil.copyMerge(fs, srcPath, fs, targetPath, false, conf, null);		
		if(copyMerge){
			System.out.println("merge successful");
		}
	}
}
