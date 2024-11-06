package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.User_id;

public class UserDAO extends DAO {

    // ユーザー検索(学生ID)
    public User_id user_student(String student_id) throws Exception {
    	User_id user = null;

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("SELECT * FROM User_account where student_id=?");
        st.setString(1, student_id);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
        	user = new User_id();
        	user.setUser_id(rs.getInt("user_id"));
        	user.setStudent_id(rs.getString("student_id"));
        	user.setTeacher_id(rs.getString("teacher_id"));
        }

        st.close();
        con.close();
        return user;
    }
    // ユーザー検索(教師ID)
    public User_id user_teacher(String teacher_id) throws Exception {
    	User_id user = null;

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("SELECT * FROM User_account where teacher_id=?");
        st.setString(1, teacher_id);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
        	user = new User_id();
        	user.setUser_id(rs.getInt("user_id"));
        	user.setStudent_id(rs.getString("student_id"));
        	user.setTeacher_id(rs.getString("teacher_id"));
        }

        st.close();
        con.close();
        return user;
    }
    // ユーザー追加機能(学生ID)
    public int user_stu_insert(String student_id) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"insert into User_account(student_id) values(?)");
		st.setString(1, student_id);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
    // ユーザー追加機能(教師ID)
    public int user_tea_insert(String teacher_id) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"insert into User_account(teacher_id) values(?)");
		st.setString(1, teacher_id);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
}