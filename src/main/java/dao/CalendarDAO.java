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
            cal.setCalendar_id(rs.getInt("calendar_id"));
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
			"insert into Calendar(user_id,calender_date,setting_date,schedule_content) values(?, ?, ?, ?)");
		st.setInt(1, user_id);
		st.setString(2, calender_date);
		st.setString(3, setting_date);
		st.setString(4, schedule_content);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
	// カレンダー削除
	public int cal_del(int calendar_id) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"delete from Calendar where calendar_id=?");
		st.setInt(1, calendar_id);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
	// カレンダー削除(ユーザーと日付と設定時間)
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
	
	// 自己管理画面でのスケジュール取得(日付の取得)
	public List<Calendar> my_management_schedule_date(int user_id,String start_date,String end_date) throws Exception {
		List<Calendar> cal_list = new ArrayList<>(); 
		Calendar cal = null;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("SELECT DISTINCT CALENDER_DATE FROM Calendar WHERE user_id=? and STR_TO_DATE(CALENDER_DATE, '%Y/%m/%d') BETWEEN ? AND ?;");
        st.setInt(1, user_id);
		st.setString(2, start_date);
		st.setString(3, end_date);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            cal = new Calendar();
            cal.setCalender_date(rs.getString("calender_date"));
            cal_list.add(cal);
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return cal_list;
	}
	// 自己管理画面でのスケジュール取得(データの取得)
	public List<Calendar> my_management_schedule(int user_id,String start_date,String end_date) throws Exception {
		List<Calendar> cal_list = new ArrayList<>(); 
		Calendar cal = null;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("SELECT * FROM Calendar WHERE user_id=? and STR_TO_DATE(CALENDER_DATE, '%Y/%m/%d') BETWEEN ? AND ?;");
        st.setInt(1, user_id);
		st.setString(2, start_date);
		st.setString(3, end_date);
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
}
