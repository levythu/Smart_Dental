package com.edu.thss.smartdental.RemoteDB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBUtil {
	private ArrayList<String> arrayList = new ArrayList<String>();
	private ArrayList<String> brrayList = new ArrayList<String>();
	private ArrayList<String> crrayList = new ArrayList<String>();
	private HttpConnSoap Soap = new HttpConnSoap();

	public static Connection getConnection() {
		Connection con = null;
		try {
			//Class.forName("org.gjt.mm.mysql.Driver");
			//con=DriverManager.getConnection("jdbc:mysql://192.168.0.106:3306/test?useUnicode=true&characterEncoding=UTF-8","root","initial");  		    
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return con;
	}

	/**
	 * 获取所有货物的信息
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getAllInfo() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		new Thread(){
			public void run()
			{
			try{
				crrayList = Soap.GetWebService("selectAllUserByDoctor", arrayList, brrayList);
			}
			catch(Exception e) {
			}
			}
		}.start();

		HashMap<String, String> tempHash = new HashMap<String, String>();
		tempHash.put("Cno", "Cno");
		tempHash.put("Cname", "Cname");
		tempHash.put("Cnum", "Cnum");
		list.add(tempHash);
		
		for (int j = 0; j < crrayList.size(); j += 3) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("Cno", crrayList.get(j));
			hashMap.put("Cname", crrayList.get(j + 1));
			hashMap.put("Cnum", crrayList.get(j + 2));
			list.add(hashMap);
		}

		return list;
	}

	/**
	 * 增加一条货物信息
	 * 
	 * @return
	 */
	public void insertUser(String username, String password,String identity, String followdoctor) {

		arrayList.clear();
		brrayList.clear();
		
		arrayList.add("username");
		arrayList.add("password");
		arrayList.add("ident");
		arrayList.add("FollowDoctor");
		brrayList.add(username);
		brrayList.add(password);
		brrayList.add(identity);
		brrayList.add(followdoctor);
		new Thread(){
			public void run()
			{
			try{
				Soap.GetWebService("insertUser", arrayList, brrayList);
			}
			catch(Exception e) {
			}
			}
		}.start();
		//Soap.GetWebServre("insertCargoInfo", arrayList, brrayList);
	}
	
	/**
	 * 删除一条货物信息
	 * 
	 * @return
	 */
	public void deleteUser(String username) {

		arrayList.clear();
		brrayList.clear();
		
		arrayList.add("username");
		brrayList.add(username);
		new Thread(){
			public void run()
			{
			try{
				Soap.GetWebService("deleteUser", arrayList, brrayList);
			}
			catch(Exception e) {
			}
			}
		}.start();
		//Soap.GetWebServre("deleteCargoInfo", arrayList, brrayList);
	}
}
