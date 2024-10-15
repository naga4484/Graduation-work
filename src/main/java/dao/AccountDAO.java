package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Studentaccount;
import bean.Teacheraccount;

public class AccountDAO extends DAO {
	
	//学生アカウント一覧取得機能
	public List<Studentaccount> student_list() throws Exception {
		List<Studentaccount> studentaccountlist=new ArrayList<>();

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(
			"select * from Student_account");
		ResultSet rs=st.executeQuery();
		while (rs.next()) {
			Studentaccount account=new Studentaccount();
			account.setStudent_id(rs.getString("student_id"));
			account.setName(rs.getString("name"));
			account.setClass_id(rs.getString("class_id"));
			account.setPassword(rs.getString("password"));
			account.setAddress(rs.getString("address"));
			account.setNickname(rs.getString("nickname"));
			account.setIcon(rs.getString("icon"));
			account.setAccount_kind("学生");
			studentaccountlist.add(account);
			}
		
		st.close();
		con.close();

		return studentaccountlist;
	}
	
	//教師アカウント一覧取得機能
		public List<Teacheraccount> teacher_list() throws Exception {
			List<Teacheraccount> teacheraccountlist=new ArrayList<>();

			Connection con=getConnection();
			PreparedStatement st=con.prepareStatement(
				"select * from Teacher_account");
			ResultSet rs=st.executeQuery();
			while (rs.next()) {
				Teacheraccount account=new Teacheraccount();
				account.setTeacher_id(rs.getString("student_id"));
				account.setName(rs.getString("name"));
				account.setClass_id(rs.getString("class_id"));
				account.setPassword(rs.getString("password"));
				account.setAddress(rs.getString("address"));
				account.setNickname(rs.getString("nickname"));
				account.setIcon(rs.getString("icon"));
				account.setAccount_kind("学生");
				teacheraccountlist.add(account);
				}
			
			st.close();
			con.close();

			return teacheraccountlist;
		}
	
	
	//学生アカウントの検索機能	
	public Studentaccount student_search(String student_id,String password)
	throws Exception {
		Studentaccount account=null;

		Connection con=getConnection();
		
		PreparedStatement st;
		st=con.prepareStatement(
				"select * from Student_account where student_id=? and password=?");
		st.setString(1, student_id);
		st.setString(2, password);
		ResultSet rs=st.executeQuery();
		
		while (rs.next()) {
			account=new Studentaccount();
			account.setStudent_id(rs.getString("student_id"));
			account.setName(rs.getString("name"));
			account.setClass_id(rs.getString("class_id"));
			account.setPassword(rs.getString("password"));
			account.setAddress(rs.getString("address"));
			account.setNickname(rs.getString("nickname"));
			account.setIcon(rs.getString("icon"));
			account.setAccount_kind("学生");
		}
		
		st.close();
		con.close();
		return account;
	}
	
	//教師アカウントの検索機能	
	public Teacheraccount teacher_search(String teacher_id,String password)
	throws Exception {
		Teacheraccount account=null;

		Connection con=getConnection();
		
		PreparedStatement st;
		st=con.prepareStatement(
				"select * from Teacher_account where teacher_id=? and password=?");
		st.setString(1, teacher_id);
		st.setString(2, password);
		ResultSet rs=st.executeQuery();
		
		while (rs.next()) {
			account=new Teacheraccount();
			account.setTeacher_id(rs.getString("teacher_id"));
			account.setName(rs.getString("name"));
			account.setClass_id(rs.getString("class_id"));
			account.setPassword(rs.getString("password"));
			account.setAddress(rs.getString("address"));
			account.setNickname(rs.getString("nickname"));
			account.setIcon(rs.getString("icon"));
			account.setAccount_kind("教師");
		}
		
		st.close();
		con.close();
		return account;
	}
	
	//学生アカウントの学生番号検索機能	
	public List<Studentaccount> student_search_no(String student_id)
	throws Exception {
		List<Studentaccount> student_no_list = new ArrayList<>(); 

		Connection con=getConnection();
		
		PreparedStatement st;
		st=con.prepareStatement(
				"select * from Student_account where student_id=?");
		st.setString(1, student_id);
		ResultSet rs=st.executeQuery();
		
		while (rs.next()) {
			Studentaccount account=new Studentaccount();
			account.setStudent_id(rs.getString("student_id"));
			account.setName(rs.getString("name"));
			account.setClass_id(rs.getString("class_id"));
			account.setPassword(rs.getString("password"));
			account.setAddress(rs.getString("address"));
			account.setNickname(rs.getString("nickname"));
			account.setIcon(rs.getString("icon"));
			account.setAccount_kind("学生");
			student_no_list.add(account);
		}
		
		st.close();
		con.close();
		return student_no_list;
	}
	
	//学生登録機能
	public int student_registration(String student_id,String password,String class_id,String name) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"insert into student_account values(?, ?, ?, ?, ?, ?, ?)");
		st.setString(1, student_id);
		st.setString(2, name);
		st.setString(3, class_id);
		st.setString(4, password);
		st.setString(5, "");
		st.setString(6, "");
		st.setString(7, "");
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
}
