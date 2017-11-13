package mytest;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HttpUrlTest {
	//git 테스트 하려고 합니다..
	public static void main(String[] args) {
		final int _EndOffset 		= 500000000;
		final int DEFAULT_TIMEOUT 	= 30000;
		long fileSize, remains, lenghtOfFile = 0;

		RandomAccessFile output 	= null;
		InputStream input 			= null;
		HttpURLConnection conn 		= null;
		
		try{
			long stime = System.currentTimeMillis();
			File file = new File("D:/test.mp4");
	
			if (file.exists() == false) {
			    file.createNewFile();
			}
	
			output 	= new RandomAccessFile(file.getAbsolutePath(), "rw"); 
			fileSize= output.length(); 
	
			output.seek(fileSize); 
	
			  
	
			URL url = new URL("http://sviftest.constore.co.kr/kthvod/SP0000053498/SG0000098489_39_POOQ_FHD_5000.mp4"); 
			conn 	= (HttpURLConnection)url.openConnection(); 
			conn.setRequestProperty("Accept-Ranges","bytes");
			conn.setRequestProperty("Range", "bytes=" + String.valueOf(946664422) + '-' + String.valueOf(1804164980)); 
	
			conn.connect(); 
	
			conn.setConnectTimeout(DEFAULT_TIMEOUT); 
	
			conn.setReadTimeout(DEFAULT_TIMEOUT); 
	
			remains = conn.getContentLength(); 
	
			lenghtOfFile = remains + fileSize; 
			//System.out.println("Range:"+fileSize + '-' + (fileSize + _EndOffset));

			input 		= conn.getInputStream(); 
			byte data[] = new byte[1024*8]; 
	
			int count = 0; 
	
			if (fileSize < lenghtOfFile) { 
	
			    while((count = input.read(data)) != -1) { 
			    	fileSize += count;	
			        output.write(data, 0, count); 
			        
			    } 
	
			} 
	
			output.close(); 
			input.close();
			long etime = System.currentTimeMillis();	
			System.out.println( "Streaming : " + ( etime - stime )/1000.0 );
			System.out.println(conn.getResponseCode());
			System.out.println("Content-Range:"	+conn.getHeaderField("Content-Range"));
			System.out.println("ContentLength:"	+conn.getHeaderField("Content-Length"));
			System.out.println("Content-Type:"	+conn.getHeaderField("Content-Type"));
			System.out.println("Last-Modified:"	+conn.getHeaderField("Last-Modified"));
			System.out.println("ETag:"+conn.getHeaderField("Etag"));
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try { 
				if (output != null) output.close(); 
				if (input != null) input.close(); 
			} catch (Exception e) {} 

		}

	}

}
