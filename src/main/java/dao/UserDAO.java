package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.User_id;

public class UserDAO extends DAO {

    // ユーザー検索(学生ID)
    public User_id user_student(String student_id) throws Exception {
    	User_id user = new User_id();

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("SELECT * FROM User_account where student_id=?");
        st.setString(1, student_id);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
        	user.setUser_id(rs.getString("user_id"));
        	user.setStudent_id(rs.getString("student_id"));
        	user.setTeacher_id(rs.getString("teacher_id"));
        }

        st.close();
        con.close();
        return user;
    }
 // ユーザー検索(教師ID)
    public User_id user_teacher(String teacher_id) throws Exception {
    	User_id user = new User_id();

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("SELECT * FROM User_account where teacher_id=?");
        st.setString(1, teacher_id);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
        	user.setUser_id(rs.getString("user_id"));
        	user.setStudent_id(rs.getString("student_id"));
        	user.setTeacher_id(rs.getString("teacher_id"));
        }

        st.close();
        con.close();
        return user;
    }
}