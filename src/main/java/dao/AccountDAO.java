package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Account;

public class AccountDAO extends DAO {
	
	
	//学生アカウントの検索機能	
	public Account student_search(String student_id,String password)
	throws Exception {
		Account account=null;

		Connection con=getConnection();
		
		PreparedStatement st;
		st=con.prepareStatement(
				"select * from student_account where student_id=? and password=?");
		st.setString(1, student_id);
		st.setString(2, password);
		ResultSet rs=st.executeQuery();
		
		while (rs.next()) {
			account=new Account();
			account.setStudent_id(rs.getString("student_id"));
			account.setName(rs.getString("name"));
			account.setGrade(rs.getString("grade"));
			account.setClass_id(rs.getString("class_id"));
			account.setPassword(rs.getString("password"));
			account.setAddress(rs.getString("address"));
			account.setNickname(rs.getString("nickname"));
			account.setIcon(rs.getString("icon"));
		}
		
		st.close();
		con.close();
		return account;
	}
}