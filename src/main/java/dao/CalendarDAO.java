package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Calendar;

public class CalendarDAO extends DAO {
	
	//カレンダー取得
	public List<Calendar> calender_list(int user_id,String selectdate) throws Exception {
		List<Calendar> cal_list = new ArrayList<>(); 
		Calendar cal = null;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from Calendar where user_id=? and calender_date=?");
        st.setInt(1, user_id);
		st.setString(2, selectdate);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            cal = new Calendar();
            cal.setUser_id(rs.getInt("user_id"));
            cal.setCalender_date(rs.getString("calender_date"));
            cal.setSetting_date(rs.getString("setting_date"));
            cal.setSchedule_content(rs.getString("schedule_content"));
            cal_list.add(cal);
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return cal_list;
	}
	
	//カレンダー重複確認用(年と時間)
	public List<Calendar> cal_search(int user_id,String selectdate,String schedule_time) throws Exception {
		List<Calendar> cal_list = new ArrayList<>(); 
		Calendar cal = null;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from Calendar where user_id=? and calender_date=? and setting_date=?");
        st.setInt(1, user_id);
		st.setString(2, selectdate);
		st.setString(3, schedule_time);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            cal = new Calendar();
            cal.setUser_id(rs.getInt("user_id"));
            cal.setCalender_date(rs.getString("calender_date"));
            cal.setSetting_date(rs.getString("setting_date"));
            cal.setSchedule_content(rs.getString("schedule_content"));
            cal_list.add(cal);
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return cal_list;
	}
	
	// カレンダー追加
	public int cal_reg(int user_id,String calender_date,String setting_date,String schedule_content) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"insert into Calendar values(?, ?, ?, ?)");
		st.setInt(1, user_id);
		st.setString(2, calender_date);
		st.setString(3, setting_date);
		st.setString(4, schedule_content);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
	// カレンダー削除(ユーザーと日付)
	public int cal_del(int user_id,String selectdate) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"delete from Calendar where user_id=? and calender_date=?");
		st.setInt(1, user_id);
		st.setString(2, selectdate);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
	// カレンダー削除(ユーザーと日付と設定時間)
		public int cal_del(int user_id,String selectdate,String delete_data) throws Exception {
			Connection con=getConnection();

			PreparedStatement st=con.prepareStatement(
				"delete from Calendar where user_id=? and calender_date=? and setting_date=?");
			st.setInt(1, user_id);
			st.setString(2, selectdate);
			st.setString(3, delete_data);
			int line=st.executeUpdate();

			st.close();
			con.close();
			return line;
		}
}
