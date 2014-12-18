/*
 * AuthorBy:qiaocy
 * 
 */
package com.edu.thss.smartdental.RemoteDB;


import java.sql.Connection;
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
	 * 获取圈子内全部患者
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getAllpatients(String DoctorName) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		arrayList.add("doctorname");
		brrayList.add(DoctorName);
		try{
			crrayList = Soap.GetWebService("selectAllUserByDoctor", arrayList, brrayList);
		}
		catch(Exception e) {
		}

		HashMap<String, String> tempHash = new HashMap<String, String>();
		tempHash.put("userid", "userid");
		tempHash.put("username", "username");
		list.add(tempHash);
		
		for (int j = 0; j < crrayList.size(); j += 2) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("userid", crrayList.get(j));
			hashMap.put("username", crrayList.get(j + 1));
			list.add(hashMap);
		}

		return list;
	}
	/**
	 * 获取全部医生
	 * 
	 * @return
	 */
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
		tempHash.put("doctorid", "doctorid");
		tempHash.put("doctorname", "doctorname");
		list.add(tempHash);
		
		for (int j = 0; j < crrayList.size(); j += 2) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("doctorid", crrayList.get(j));
			hashMap.put("doctorname", crrayList.get(j + 1));
			list.add(hashMap);
		}

		return list;
	}
	
	/**
	 * 获得一个用户所有圈子医生列表
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> selectDoctorsByname(String userName) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		arrayList.add("userName");
		brrayList.add(userName);
			
		try{
			crrayList = Soap.GetWebService("selectDoctorsByname", arrayList, brrayList);
		}
		catch(Exception e) {
		}
			

		HashMap<String, String> tempHash = new HashMap<String, String>();
		tempHash.put("doctorid", "doctorid");
		tempHash.put("doctorname", "doctorname");
		list.add(tempHash);
		
		for (int j = 0; j < crrayList.size(); j += 2) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("doctorid", crrayList.get(j));
			hashMap.put("doctorname", crrayList.get(j + 1));
			list.add(hashMap);
		}

		return list;
	}
	
	/**
	 * 注册用户
	 * 
	 * @return
	 */
	public String insertUser(String username, String password,String identity) {

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		
		arrayList.add("username");
		arrayList.add("password");
		arrayList.add("ident");
		brrayList.add(username);
		brrayList.add(password);
		brrayList.add(identity);
		try{
			crrayList = Soap.GetWebService("insertUser", arrayList, brrayList);
		}
		catch(Exception e) {
		}
		if(crrayList.size() == 0){
			return "fail to connect to Database";
		}
		return crrayList.get(0);
	}
	
	/**
	 * 登录
	 * if log in success return true
	 * else if user not exist return user does not exist
	 * else if wrong password return wrong password
	 * else return inner error
	 * @return
	 */
	public String login(String username, String password)
	{
		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		
		arrayList.add("username");
		arrayList.add("password");
		brrayList.add(username);
		brrayList.add(password);
		try{
			crrayList = Soap.GetWebService("login", arrayList, brrayList);
		}
		catch(Exception e) {
		}
		if(crrayList.size() == 0){
			return "fail to connect to Database";
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
		if(crrayList.size() == 0){
			return "fail to connect to Database";
		}
		return crrayList.get(0);
	}
	/**
	 * 加入圈子
	 * 
	 * @return
	 */
	public String joinCircle(String username,String circlepassword,String doctorname){
		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		arrayList.add("username");
		arrayList.add("doctorpassword");
		arrayList.add("doctorname");
		brrayList.add(username);
		brrayList.add(circlepassword);
		brrayList.add(doctorname);
		try{
			crrayList = Soap.GetWebService("joinDoctor", arrayList, brrayList);
		}
		catch(Exception e) {
		}
		if(crrayList.size() == 0){
			return "fail to connect to Database";
		}
		return crrayList.get(0);
	}
	/**
	 * 设置圈子密码
	 * 
	 * @return
	 */
	public String SetcirclePassword(String oldpassword, String newpassword, String doctorname){
		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		arrayList.add("oldpassword");
		arrayList.add("newpassword");
		arrayList.add("doctorname");
		brrayList.add(oldpassword);
		brrayList.add(newpassword);
		brrayList.add(doctorname);
		try{
			crrayList = Soap.GetWebService("SetcirclePassword", arrayList, brrayList);
		}
		catch(Exception e) {
		}
		if(crrayList.size() == 0){
			return "fail to connect to Database";
		}
		return crrayList.get(0);
	}
	
	/**
	 * 踢出圈子
	 * 
	 * @return
	 */
	public String kickout(String username, String doctorname){
		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		arrayList.add("username");
		arrayList.add("doctorname");
		brrayList.add(username);
		brrayList.add(doctorname);
		try{
			crrayList = Soap.GetWebService("SetcirclePassword", arrayList, brrayList);
		}
		catch(Exception e) {
		}
		if(crrayList.size() == 0){
			return "fail to connect to Database";
		}
		return crrayList.get(0);
	}
	/**
	 * 获取圈子内全部帖子信息
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
		tempHash.put("PostId", "PostId");
		tempHash.put("tag", "tag");
		tempHash.put("doctorid", "doctorid");
		list.add(tempHash);
		
		for (int j = 0; j < crrayList.size(); j += 7) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("postname", crrayList.get(j));
			hashMap.put("postcontent", crrayList.get(j + 1));
			hashMap.put("time", crrayList.get(j + 2));
			hashMap.put("author", crrayList.get(j + 3));
			hashMap.put("PostId", crrayList.get(j + 4));
			hashMap.put("tag", crrayList.get(j + 5));
			hashMap.put("doctorid", crrayList.get(j + 6));
			list.add(hashMap);
		}

		return list;
	}

	/**
	 * 通过id获取帖子
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> selectPostById(int PostId) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		arrayList.add("PostId");
		brrayList.add(Integer.toString(PostId));
		try{
			
			crrayList = Soap.GetWebService("selectPostById", arrayList, brrayList);
		}
		catch(Exception e) {
		}

		HashMap<String, String> tempHash = new HashMap<String, String>();
		tempHash.put("postname", "postname");
		tempHash.put("postcontent", "postcontent");
		tempHash.put("time", "time");
		tempHash.put("author", "author");
		tempHash.put("PostId", "PostId");
		tempHash.put("tag", "tag");
		tempHash.put("doctorid", "doctorid");
		list.add(tempHash);
		
		for (int j = 0; j < crrayList.size(); j += 7) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("postname", crrayList.get(j));
			hashMap.put("postcontent", crrayList.get(j + 1));
			hashMap.put("time", crrayList.get(j + 2));
			hashMap.put("author", crrayList.get(j + 3));
			hashMap.put("PostId", crrayList.get(j + 4));
			hashMap.put("tag", crrayList.get(j + 5));
			hashMap.put("doctorid", crrayList.get(j + 6));
			list.add(hashMap);
		}

		return list;
	}
	
	/**
	 * 获取一个用户的全部帖子
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> selectAllPostByUser(String username) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		arrayList.add("username");
		brrayList.add(username);
		try{
			
			crrayList = Soap.GetWebService("selectAllPostByUser", arrayList, brrayList);
		}
		catch(Exception e) {
		}

		HashMap<String, String> tempHash = new HashMap<String, String>();
		tempHash.put("postname", "postname");
		tempHash.put("postcontent", "postcontent");
		tempHash.put("time", "time");
		tempHash.put("author", "author");
		tempHash.put("PostId", "PostId");
		tempHash.put("tag", "tag");
		list.add(tempHash);
		
		for (int j = 0; j < crrayList.size(); j += 6) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("postname", crrayList.get(j));
			hashMap.put("postcontent", crrayList.get(j + 1));
			hashMap.put("time", crrayList.get(j + 2));
			hashMap.put("author", crrayList.get(j + 3));
			hashMap.put("PostId", crrayList.get(j + 4));
			hashMap.put("tag", crrayList.get(j + 5));
			list.add(hashMap);
		}

		return list;
	}
	
	/**
	 * 获得用户收藏帖子列表
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> selectcollectPostid(String userName) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		arrayList.add("userName");
		brrayList.add(userName);
		try{
			
			crrayList = Soap.GetWebService("selectcollectPostid", arrayList, brrayList);
		}
		catch(Exception e) {
		}

		HashMap<String, String> tempHash = new HashMap<String, String>();
		tempHash.put("postid", "postname");
		list.add(tempHash);
		
		for (int j = 0; j < crrayList.size(); j += 1) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("postid", crrayList.get(j));
			list.add(hashMap);
		}

		return list;
	}
	/**
	 * 收藏帖子
	 * 
	 * @return
	 */
	public String collectpost(String username,int PostId){
		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		
		arrayList.add("username");
		arrayList.add("PostId");
		brrayList.add(username);
		brrayList.add(Integer.toString(PostId));
		try{
			crrayList = Soap.GetWebService("collectpost", arrayList, brrayList);
		}
		catch(Exception e) {
		}		
		if(crrayList.size() == 0){
			return "fail to connect to Database";
		}
		return crrayList.get(0);
	}
	
	/**
	 * 取消收藏
	 * 
	 * @return
	 */
	public String deletecollectpost(String username,int PostId){
		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		
		arrayList.add("username");
		arrayList.add("PostId");
		brrayList.add(username);
		brrayList.add(Integer.toString(PostId));
		try{
			crrayList = Soap.GetWebService("deletecollectpost", arrayList, brrayList);
		}
		catch(Exception e) {
		}		
		if(crrayList.size() == 0){
			return "fail to connect to Database";
		}
		return crrayList.get(0);
	}
	/**
	 * 发新帖子
	 * 
	 * @return
	 */
	public String insertPost(String PostName, String postContent,String username, String doctorid, String tag) {

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		
		arrayList.add("PostName");
		arrayList.add("postContent");
		arrayList.add("username");
		arrayList.add("doctorid");
		arrayList.add("tag");
		brrayList.add(PostName);
		brrayList.add(postContent);
		brrayList.add(username);
		brrayList.add(doctorid);
		brrayList.add(tag);
		try{
			crrayList = Soap.GetWebService("insertPost", arrayList, brrayList);
		}
		catch(Exception e) {
		}		
		if(crrayList.size() == 0){
			return "fail to connect to Database";
		}
		return crrayList.get(0);
	}
	
	/**
	 * 删除帖子
	 * 
	 * @return
	 */
	public String deletePost(String postname) {

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		
		arrayList.add("postname");
		brrayList.add(postname);
		try{
			crrayList = Soap.GetWebService("deletePost", arrayList, brrayList);
		}
		catch(Exception e) {
		}
		if(crrayList.size() == 0){
			return "fail to connect to Database";
		}
		return crrayList.get(0);
	}
	
	/**
	 * 获取帖子内全部评论
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getAllComments(int PostId) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		arrayList.add("PostId");
		brrayList.add(Integer.toString(PostId));
			
		try{
			crrayList = Soap.GetWebService("selectAllCommentsByPostId", arrayList, brrayList);
		}
		catch(Exception e) {
		}
			

		HashMap<String, String> tempHash = new HashMap<String, String>();
		tempHash.put("commentusername", "commentusername");
		tempHash.put("commenttype", "commenttype");
		tempHash.put("commenttitle", "commenttitle");
		tempHash.put("commentcontent", "commentcontent");
		tempHash.put("replytouser", "replytouser");
		tempHash.put("time","time");
		list.add(tempHash);
		
		for (int j = 0; j < crrayList.size(); j += 6) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("commentusername", crrayList.get(j));
			hashMap.put("commenttype", crrayList.get(j + 1));
			hashMap.put("commenttitle", crrayList.get(j + 2));
			hashMap.put("commentcontent", crrayList.get(j + 3));
			hashMap.put("replytouser", crrayList.get(j + 4));
			hashMap.put("time", crrayList.get(j + 4));
			list.add(hashMap);
		}

		return list;
	}
	/**
	 * 新增评论
	 * 
	 * @return
	 */
	public String insertComment(String postid, String commentContent, String username, String CommentType, String ReplyUserName) {

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		
		arrayList.add("postId");
		arrayList.add("commentContent");
		arrayList.add("username");
		arrayList.add("CommentType");
		arrayList.add("ReplyUserName");
		brrayList.add(postid);
		brrayList.add(commentContent);
		brrayList.add(username);
		brrayList.add(CommentType);	
		brrayList.add(ReplyUserName);
		try{
			crrayList = Soap.GetWebService("insertComment", arrayList, brrayList);
		}
		catch(Exception e) {
		}	
		if(crrayList.size() == 0){
			return "fail to connect to Database";
		}
		return crrayList.get(0);
	}
	/**
	 * 删除评论
	 * 
	 * @return
	 */
	public String deleteComment(String commentId) {

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		
		arrayList.add("id");
		brrayList.add(commentId);
		try{
			crrayList = Soap.GetWebService("deleteComment", arrayList, brrayList);
		}
		catch(Exception e) {
		}
		if(crrayList.size() == 0){
			return "fail to connect to Database";
		}
		return crrayList.get(0);
	}
	/**
	 * 获得用户全部新消息
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> selectAllUnreadNewsByUsername(String username) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		arrayList.add("username");
		brrayList.add(username);
		try{
			
			crrayList = Soap.GetWebService("selectAllUnreadNewsByUsername", arrayList, brrayList);
		}
		catch(Exception e) {
		}

		HashMap<String, String> tempHash = new HashMap<String, String>();
		tempHash.put("newsId", "newsId");
		tempHash.put("newstype", "newstype");
		tempHash.put("username", "username");
		tempHash.put("content", "content");
		tempHash.put("time", "time");
		list.add(tempHash);
		
		for (int j = 0; j < crrayList.size(); j += 5) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("newsId", crrayList.get(j));
			hashMap.put("newstype", crrayList.get(j + 1));
			hashMap.put("username", crrayList.get(j + 2));
			hashMap.put("content", crrayList.get(j + 3));
			hashMap.put("time", crrayList.get(j + 4));
			list.add(hashMap);
		}

		return list;
	}
	/**
	 * 获取用户全部已读消息
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> selectAllReadNewsByUsername(String username) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		arrayList.add("username");
		brrayList.add(username);
		try{
			
			crrayList = Soap.GetWebService("selectAllReadNewsByUsername", arrayList, brrayList);
		}
		catch(Exception e) {
		}

		HashMap<String, String> tempHash = new HashMap<String, String>();
		tempHash.put("newsId", "newsId");
		tempHash.put("newstype", "newstype");
		tempHash.put("username", "username");
		tempHash.put("content", "content");
		tempHash.put("time", "time");
		list.add(tempHash);
		
		for (int j = 0; j < crrayList.size(); j += 5) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("newsId", crrayList.get(j));
			hashMap.put("newstype", crrayList.get(j + 1));
			hashMap.put("username", crrayList.get(j + 2));
			hashMap.put("content", crrayList.get(j + 3));
			hashMap.put("time", crrayList.get(j + 4));
			list.add(hashMap);
		}

		return list;
	}
	
	/**
	 * 新建一条消息
	 * 
	 * @return
	 */
	public String insertNews(String newstype, String username, String replytouser, String haveread,String newscontent) {

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		
		arrayList.add("newstype");
		arrayList.add("username");
		arrayList.add("replytouser");
		arrayList.add("haveread");
		arrayList.add("newscontent");
		brrayList.add(newstype);
		brrayList.add(username);
		brrayList.add(replytouser);
		brrayList.add(haveread);	
		brrayList.add(newscontent);
		try{
			crrayList = Soap.GetWebService("insertNews", arrayList, brrayList);
		}
		catch(Exception e) {
		}	
		if(crrayList.size() == 0){
			return "fail to connect to Database";
		}
		return crrayList.get(0);
	}
	
	/**
	 * 鏇磋娑堟伅鐘舵�佷负宸茶
	 * 
	 * @return
	 */
	public String haveread(String newsid) {

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		
		arrayList.add("newsid");
		brrayList.add(newsid);
		try{
			crrayList = Soap.GetWebService("haveread", arrayList, brrayList);
		}
		catch(Exception e) {
		}
		if(crrayList.size() == 0){
			return "fail to connect to Database";
		}
		return crrayList.get(0);
	}
}
