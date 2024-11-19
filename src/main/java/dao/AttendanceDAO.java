package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Attendance;

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
	//特定データの探索用(クラスID)
	public List<Attendance> class_search(String class_id,String sdf) throws Exception { 
		List<Attendance> at_list = new ArrayList<>(); 
		
		Connection con=getConnection();
		Attendance at;
		
		PreparedStatement st;
		st=con.prepareStatement(
				"select sa.student_id,sa.name,at.attendance_kind_id,at.class_id,at.attendance_date from Attendance AS at JOIN Student_account AS sa ON at.student_id = sa.student_id where at.class_id=? and attendance_date=?");
		st.setString(1, class_id);
		st.setString(2, sdf);
		ResultSet rs=st.executeQuery();
		
		while (rs.next()) {
			at = new Attendance();
        	at.setStudent_id(rs.getString("student_id"));
        	at.setAttendance_kind_id(rs.getString("attendance_kind_id"));
        	at.setAttendance_date(rs.getString("attendance_date"));
        	at.setClass_id(rs.getString("at.class_id"));
            at.setName(rs.getString("name"));
            at_list.add(at);
		}
		
		st.close();
		con.close();
		return at_list;
	}
	//特定データの探索用(学生名)
	public List<Attendance> name_search(String name,String sdf) throws Exception { 
		List<Attendance> at_list = new ArrayList<>(); 
		
		Connection con=getConnection();
		Attendance at;
		
		PreparedStatement st;
		st=con.prepareStatement(
				"select sa.student_id,sa.name,at.attendance_kind_id,at.class_id,at.attendance_date from Attendance AS at JOIN Student_account AS sa ON at.student_id = sa.student_id where name=? and attendance_date=?");
		st.setString(1, name);
		st.setString(2, sdf);
		ResultSet rs=st.executeQuery();
		
		while (rs.next()) {
			at = new Attendance();
			at.setStudent_id(rs.getString("student_id"));
        	at.setAttendance_kind_id(rs.getString("attendance_kind_id"));
        	at.setAttendance_date(rs.getString("attendance_date"));
        	at.setClass_id(rs.getString("at.class_id"));
            at.setName(rs.getString("name"));
            at_list.add(at);
		}
		
		st.close();
		con.close();
		return at_list;
	}
	//欠席日数計算用(計算はjsp側で行う)
	public List<Attendance> attendance_calculation(String student_id) throws Exception {
		Connection con=getConnection();
		List<Attendance> at_list = new ArrayList<>(); 
		Attendance at;

		PreparedStatement st=con.prepareStatement(
			"SELECT * FROM Attendance where student_id=? ORDER BY CAST(attendance_id AS UNSIGNED) DESC limit 30;");
		st.setString(1,student_id);
		ResultSet rs = st.executeQuery();
		int num = 0;

        while (rs.next()) {
        	at = new Attendance();
        	at.setAttendance_id(rs.getString("attendance_id"));
        	at.setStudent_id(rs.getString("student_id"));
        	at.setAttendance_kind_id(rs.getString("attendance_kind_id"));
        	at.setAttendance_date(rs.getString("attendance_date"));
        	at.setClass_id(rs.getString("class_id"));
            at.setNote(rs.getString("note"));
            at_list.add(at);
        }

        rs.close(); 
        st.close();
        con.close(); 

		return at_list;
	}
}