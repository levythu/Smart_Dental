package com.edu.thss.smartdental.RemoteDB;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HttpConnSoap {
	public ArrayList<String> GetWebService(String methodName, ArrayList<String> Parameters, ArrayList<String> ParValues) {
		ArrayList<String> Values = new ArrayList<String>();
		
		//指定URL地址，我这里使用的是常量。  
        //如：String ServerUrl = "http://10.0.2.2:11125/Service1.asmx";  
        String ServerUrl = "http://192.168.1.103/Service1.asmx";  
  
        //soapAction = 命名空间 + 方法名  
        String soapAction = "http://tempuri.org/" + methodName;  
  
        //拼凑requestData  
        String soap = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"  
                      + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"  
                      + "<soap:Body />";  
        String tps, vps, ts;  
        String mreakString = "";  
        mreakString = "<" + methodName + " xmlns=\"http://tempuri.org/\">";  
        for (int i = 0; i < Parameters.size(); i++)  
        {  
            tps = Parameters.get (i).toString();  
            //设置该方法的参数为.net webService中的参数名称  
            vps = ParValues.get (i).toString();  
            ts = "<" + tps + ">" + vps + "</" + tps + ">";  
            mreakString = mreakString + ts;  
        }  
        mreakString = mreakString + "</" + methodName + ">";  
        String soap2 = "</soap:Envelope>";  
        String requestData = soap + mreakString + soap2;  
        //其上所有的数据都是在拼凑requestData，即向服务器发送的数据  
  
        try  
        {  
            URL url = new URL (ServerUrl); //指定服务器地址 
             
            //InputStream is = null;  
            HttpURLConnection con = (HttpURLConnection) url.openConnection();//打开链接
            //con.connect();  
            //is = con.getInputStream();  
            
            byte[] bytes = requestData.getBytes ("utf-8"); //指定编码格式，可以解决中文乱码问题  
            con.setDoInput (true); //指定该链接是否可以输入  
            con.setDoOutput (true); //指定该链接是否可以输出  
            con.setUseCaches (false); //指定该链接是否只用caches  
            con.setConnectTimeout (6000); // 设置超时时间  
            con.setRequestMethod ("GET"); //指定发送方法名，包括Post和Get。  
            con.setRequestProperty ("Content-Type", "text/xml;charset=utf-8"); //设置（发送的）内容类型  
            con.setRequestProperty ("SOAPAction", soapAction); //指定soapAction  
            con.setRequestProperty ("Content-Length", "" + bytes.length); //指定内容长度 
            //con.connect();
            
            //发送数据  
            OutputStream outStream = con.getOutputStream();  
            outStream.write (bytes);  
            outStream.flush();  
            outStream.close();  
  
            //获取数据  
            InputStream inputStream = con.getInputStream();  
            Values = inputStreamtovaluelist(inputStream,methodName);
            return Values;  
  
            /** 
             * 此类到此结束了，比原来的HttpConnSoap还短，因为这里没有对返回的数据做解析。数据完全都保存在了inputStream中。 
             * 而原来的类是将数据解析成了ArrayList 
             * <String>格式返回。显然，这样无法解决我们上面的需求（返回值是复杂类型的List） 
             */  
        }catch (Exception e)  
        {  
            e.printStackTrace();  
            return Values;  
        }  
	}

	public ArrayList<String> inputStreamtovaluelist(InputStream in, String MonthsName) throws IOException {
		StringBuffer out = new StringBuffer();
		String s1 = "";
		byte[] b = new byte[4096];
		ArrayList<String> Values = new ArrayList<String>();
		Values.clear();

		for (int n; (n = in.read(b)) != -1;) {
			s1 = new String(b, 0, n);
			out.append(s1);
		}

		System.out.println(out);
		String[] s13 = s1.split("><");
		//String[] s13 = out.toString().split("><");
		String ifString = MonthsName + "Result";
		String TS = "";
		String vs = "";

		Boolean getValueBoolean = false;
		for (int i = 0; i < s13.length; i++) {
			TS = s13[i];
			System.out.println(TS);
			int j, k, l;
			j = TS.indexOf(ifString);
			k = TS.lastIndexOf(ifString);

			if (j >= 0) {
				System.out.println(j);
				if (getValueBoolean == false) {
					getValueBoolean = true;
				} else {

				}

				if ((j >= 0) && (k > j)) {
					System.out.println("FFF" + TS.lastIndexOf("/" + ifString));
					//System.out.println(TS);
					l = ifString.length() + 1;
					vs = TS.substring(j + l, k - 2);
					//System.out.println("fff"+vs);
					Values.add(vs);
					System.out.println("退出" + vs);
					getValueBoolean = false;
					return Values;
				}

			}
			if (TS.lastIndexOf("/" + ifString) >= 0) {
				getValueBoolean = false;
				return Values;
			}
			if ((getValueBoolean) && (TS.lastIndexOf("/" + ifString) < 0) && (j < 0)) {
				k = TS.length();
				//System.out.println(TS);
				vs = TS.substring(7, k - 8);
				//System.out.println("f"+vs);
				Values.add(vs);
			}

		}

		return Values;
	}

}
