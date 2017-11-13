package mytest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class UrlTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			//String url 	= "http://211.42.150.111/goodsRcv.do?";
			String url 	= "http://cdslogtest.constore.co.kr/goodsdnlog.html?";
			String param= "";
		try{
			param= "sellgb=A&hashcode=f300d3dc5fa624a9f7abf9e42eb536c5&oid=oid12345657890&coopcd=AI001268&amt=1000&startdt=20080723123412&enddt=20080724123412&sellgoodscd=MOV123454321&filesize=123456&filenm=DivxRip-IronII.avi&contnm="+URLEncoder.encode("아이언맨2","UTF-8")+"&svcid=AI001268&paytype=PAY&userid=kth@paran.com";
			//param= "qs=<GoodsRcv><action>GH1</action><svcid>DUM0000003</svcid></GoodsRcv>";
		}catch(Exception e){
			e.printStackTrace();
		}
		String result = getConnection(url,param);
		System.out.println("result:"+result);
	}
	public static String getConnection(String Req_url,String param) {
		PrintWriter 		pw 	= null;
		BufferedWriter 		bw	= null;
		BufferedReader 		br	= null;
		HttpURLConnection 	con = null;
		StringBuffer resultXml = new StringBuffer();
	    try{
		    URL url=new URL(Req_url+param);
			con=(HttpURLConnection)url.openConnection();
			
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setUseCaches(false);
            con.setDefaultUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setRequestMethod("GET");
            
            //pw = new PrintWriter(new OutputStreamWriter(con.getOutputStream(),"UTF-8"));
            //pw.write(param);
            //pw.flush();
            
		    String line2="";
		    br=new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
		   
		    while((line2=br.readLine())!=null){
		    	resultXml.append(line2);
		    }
		    
	    }catch(Exception e){
			e.printStackTrace();
			
		}finally{
			 if(pw != null) try { pw.close(); } catch(Exception e) {}
			 if(bw != null) try { bw.close(); } catch(Exception e) {}
		     if(br != null) try { br.close(); } catch(Exception e) {}
		     con.disconnect();
		}
		    return resultXml.toString();
	   
   }
}
