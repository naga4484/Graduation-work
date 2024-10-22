package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO extends DAO {
	
	//出欠登録
	public int attendance_registration(String attendance_id,String student_id,String attendance_kind_id,String attendance_date,String class_id,String note) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"insert into Attendance values(?, ?, ?, ?, ?, ?)");
		st.setString(1, attendance_id);
		st.setString(2, student_id);
		st.setString(3, attendance_kind_id);
		st.setString(4, attendance_date);
		st.setString(5, class_id);
		st.setString(6, note);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
	//出欠更新用
	public int attendance_update(String sdf,String student_id,String attendance_kind_id,String note) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"update Attendance set attendance_kind_id = ?,note = ? where attendance_date = ? and student_id = ?");
		st.setString(3, sdf);
		st.setString(4, student_id);
		st.setString(1, attendance_kind_id);
		st.setString(2, note);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
	
	//ID作成用
	public List<String> getattendanceid() throws Exception {
				List<String> id = new ArrayList<>(); 

		        Connection con = getConnection();

		        PreparedStatement st = con.prepareStatement("select distinct attendance_id from Attendance");
		        ResultSet rs = st.executeQuery();

		        while (rs.next()) {
		            id.add(rs.getString("attendance_id")); 
		        }

		        rs.close(); 
		        st.close();
		        con.close(); 
		        
		        return id;
	}
	
	//特定データの探索用
	public Boolean student_search(String student_id,String sdf) throws Exception { 
			Boolean i = false;
			
			Connection con=getConnection();
			
			PreparedStatement st;
			st=con.prepareStatement(
					"select * from Attendance where student_id=? and attendance_date=?");
			st.setString(1, student_id);
			st.setString(2, sdf);
			ResultSet rs=st.executeQuery();
			
			while (rs.next()) {
				i = true;
			}
			
			st.close();
			con.close();
			return i;
	}
}