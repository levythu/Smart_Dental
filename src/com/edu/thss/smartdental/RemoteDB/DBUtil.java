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
		return con;
	}

	/**
	 * 获取所有用户的信息
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getAllpatients(String DoctorName) {
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

	public List<HashMap<String, String>> getAllDoctors() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
			
		try{
			crrayList = Soap.GetWebService("selectAllDoctors", arrayList, brrayList);
		}
		catch(Exception e) {
		}
			

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
	 * 增加一个用户
	 * 
	 * @return
	 */
	public String insertUser(String username, String password,String identity, String followdoctor) {

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		
		arrayList.add("username");
		arrayList.add("password");
		arrayList.add("ident");
		arrayList.add("followdoctor");
		brrayList.add(username);
		brrayList.add(password);
		brrayList.add(identity);
		brrayList.add(followdoctor);
		try{
			crrayList = Soap.GetWebService("insertUser", arrayList, brrayList);
		}
		catch(Exception e) {
		}
		return crrayList.get(0);
	}
	
	/**
	 * 删除一个用户
	 * 
	 * @return
	 */
	public String deleteUser(String username) {

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		
		arrayList.add("username");
		brrayList.add(username);
		try{
			crrayList = Soap.GetWebService("deleteUser", arrayList, brrayList);
		}
		catch(Exception e) {
		}
		return crrayList.get(0);
	}
	
	/**
	 * 获取所有帖子的信息
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getAllPostInfo(int id) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		arrayList.add("id");
		brrayList.add(Integer.toString(id));
		try{
			
			crrayList = Soap.GetWebService("selectAllPostByDoctor", arrayList, brrayList);
		}
		catch(Exception e) {
		}

		HashMap<String, String> tempHash = new HashMap<String, String>();
		tempHash.put("postname", "postname");
		tempHash.put("postcontent", "postcontent");
		tempHash.put("time", "time");
		tempHash.put("author", "author");
		list.add(tempHash);
		
		for (int j = 0; j < crrayList.size(); j += 4) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("postname", crrayList.get(j));
			hashMap.put("postcontent", crrayList.get(j + 1));
			hashMap.put("time", crrayList.get(j + 2));
			hashMap.put("author", crrayList.get(j + 3));
			list.add(hashMap);
		}

		return list;
	}

	/**
	 * 新建帖子
	 * 
	 * @return
	 */
	public void insertPost(String PostName, String postContent,String username, String doctorname) {

		arrayList.clear();
		brrayList.clear();
		
		arrayList.add("PostName");
		arrayList.add("postContent");
		arrayList.add("username");
		arrayList.add("doctorname");
		brrayList.add(PostName);
		brrayList.add(postContent);
		brrayList.add(username);
		brrayList.add(doctorname);
		new Thread(){
			public void run()
			{
			try{
				Soap.GetWebService("insertPost", arrayList, brrayList);
			}
			catch(Exception e) {
			}
			}
		}.start();
		//Soap.GetWebServre("insertCargoInfo", arrayList, brrayList);
	}
	
	/**
	 * 删除帖子
	 * 
	 * @return
	 */
	public void deletePost(String postname) {

		arrayList.clear();
		brrayList.clear();
		
		arrayList.add("postname");
		brrayList.add(postname);
		new Thread(){
			public void run()
			{
			try{
				Soap.GetWebService("deletePost", arrayList, brrayList);
			}
			catch(Exception e) {
			}
			}
		}.start();
		//Soap.GetWebServre("deleteCargoInfo", arrayList, brrayList);
	}
}
