package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import bean.Timetable;
import bean.Timetable_template;

public class TimetableDAO extends DAO {
	//時間割の取得(クラスと日にち)
	public List<Timetable> timetable_search(String class_id,String data) throws Exception {
		List<Timetable> timetableList = new ArrayList<>(); 
		Timetable timetable;
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("select * from Timetable where class_id=? and data=?");
        st.setString(1, class_id);
        st.setString(2, data);
        ResultSet rs = st.executeQuery();
        
        while (rs.next()) {
        	timetable = new Timetable();
        	timetable.setTimetable_id(rs.getString("timetable_id"));
        	timetable.setSubject_id(rs.getString("subject_id"));
        	timetable.setSubject_color(rs.getString("subject_color"));
        	timetable.setTimetable_hour(rs.getString("timetable_hour"));
        	timetable.setTeacher_id(rs.getString("teacher_id"));
        	timetable.setData(rs.getString("data"));
        	timetable.setClass_id(rs.getString("class_id"));
        	timetableList.add(timetable);
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return timetableList;
	}
	
	//時間割一覧の取得(クラス+教師ID検索)
	public List<Timetable> timetable_class(String class_id,String teacher_id) 
	throws Exception {
		List<Timetable> timetableList = new ArrayList<>(); 
		Timetable timetable;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from Timetable where class_id=? and teacher_id=?");
        st.setString(1, class_id);
        st.setString(2, teacher_id);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
        	timetable = new Timetable();
        	timetable.setTimetable_id(rs.getString("timetable_id"));
        	timetable.setSubject_id(rs.getString("subject_id"));
        	timetable.setSubject_color(rs.getString("subject_color"));
        	timetable.setTimetable_hour(rs.getString("timetable_hour"));
        	timetable.setTeacher_id(rs.getString("teacher_id"));
        	timetable.setData(rs.getString("data"));
        	timetable.setClass_id(rs.getString("class_id"));
        	timetableList.add(timetable); 
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return timetableList;
	}
	public List<Timetable> timetable_distinct() 
	throws Exception {
		List<Timetable> timetableList = new ArrayList<>(); 
		Timetable timetable;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select distinct timetable_id from Timetable");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
        	timetable = new Timetable();
        	timetable.setTimetable_id(rs.getString("timetable_id"));
        	timetableList.add(timetable);
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return timetableList;
	}
	public int time_Initial_settings(String timetable_id,String class_id,String teacher_id) 
	throws Exception {
		
		//月の取得
		LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        int line = 0;
        Connection con = getConnection();
        PreparedStatement st = null;
        
        //二年分のデータ作成
    	for(int o=0;o<2;o++) {
    		//一年度分の作成(4月から12月分)
	        for(int i = 4;i <= 12;i++) {
	        	LocalDate for_date = LocalDate.of(year ,i ,1);
	        	int Month_of_days = for_date.lengthOfMonth(); 
	        	for(int j = 1;j <= Month_of_days;j++) {
	        		String create_date = LocalDate.of(year, i, j).format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
	        		for(int k = 1;k <= 4;k++) {
	                    st=con.prepareStatement("insert into Timetable(timetable_id,subject_color,timetable_hour,teacher_id,data,class_id) values(?, ?, ?, ?, ?, ?)");
	            		st.setString(1, timetable_id);
	            		st.setString(2, "#cce5ff");
	            		st.setString(3, Integer.toString(k));
	            		st.setString(4, teacher_id);
	            		st.setString(5, create_date);
	            		st.setString(6, class_id);
	            		line=st.executeUpdate();
	        		}
	        	}
	        }
	        year++;
	        //一年度分の作成(1月から3月分)
	        for(int i = 1;i <= 3;i++) {
	        	LocalDate for_date = LocalDate.of(year ,i ,1);
	        	int Month_of_days = for_date.lengthOfMonth(); 
	        	for(int j = 1;j <= Month_of_days;j++) {
	        		String create_date = LocalDate.of(year, i, j).format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
	        		for(int k = 1;k <= 4;k++) {
	        			st=con.prepareStatement("insert into Timetable(timetable_id,subject_color,timetable_hour,teacher_id,data,class_id) values(?, ?, ?, ?, ?, ?)");
	            		st.setString(1, timetable_id);
	            		st.setString(2, "#cce5ff");
	            		st.setString(3, Integer.toString(k));
	            		st.setString(4, teacher_id);
	            		st.setString(5, create_date);
	            		st.setString(6, class_id);
	            		line=st.executeUpdate();
	            		
	        		}
	        	}
	        }
    	}     
    	st.close();
		con.close(); 
		return line;
	}
	//日付セレクト用
	public List<String> timetable_select(String class_id,String teacher_id) 
	throws Exception {
		List<String> timetable_data = new ArrayList<>(); 

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select distinct data from Timetable where class_id=? and teacher_id=?");
        st.setString(1, class_id);
        st.setString(2, teacher_id);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
        	timetable_data.add(rs.getString("data").substring(0, 8)); 
	    }
        
        List<String> timetable_data_distinct = new ArrayList<String>(new LinkedHashSet<>(timetable_data));
        rs.close(); 
        st.close();
        con.close(); 
        
        return timetable_data_distinct;
	}
	//日付対応表示用
	public List<Timetable> timetable_data_select(String class_id,String teacher_id,String data) 
	throws Exception {
		List<Timetable> timetableList = new ArrayList<>(); 
		Timetable timetable;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from Timetable where class_id = ? AND teacher_id = ? AND data like ?");
        st.setString(1, class_id);
        st.setString(2, teacher_id);
        st.setString(3, data + "%");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
        	timetable = new Timetable();
        	timetable.setTimetable_id(rs.getString("timetable_id"));
        	timetable.setSubject_id(rs.getString("subject_id"));
        	timetable.setSubject_color(rs.getString("subject_color"));
        	timetable.setTimetable_hour(rs.getString("timetable_hour"));
        	timetable.setTeacher_id(rs.getString("teacher_id"));
        	timetable.setData(rs.getString("data"));
        	timetable.setClass_id(rs.getString("class_id"));
        	timetableList.add(timetable); 
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return timetableList;
	}
	//時間割設定用
	public int timetable_update(String subject_id,String timetable_hour,String teacher_id,String class_id,String data) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"update Timetable set subject_id=? where timetable_hour=? and teacher_id=? and class_id=? and data = ?");
		st.setString(1, subject_id);
        st.setString(2, timetable_hour);
        st.setString(3, teacher_id);
        st.setString(4, class_id);
        st.setString(5, data);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
	//科目削除用
	public int timetable_update(String subject_id) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"update Timetable set subject_id=? where subject_id=?");
		st.setString(1, null);
        st.setString(2, subject_id);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
	
	//ここからは、時間割テンプレートの機能のDAO
	public int timetable_template_registration(String timetable_name,String subject_id,String timetable_hour,String teacher_id) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"insert into Timetable_template(subject_id,timetable_hour,teacher_id,template_name) values(?,?,?,?)");
		st.setString(1, subject_id);
        st.setString(2, timetable_hour);
        st.setString(3, teacher_id);
        st.setString(4, timetable_name);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
	//時間割テンプレートの一覧の取得
	public List<Timetable_template> timetable_template(String teacher_id) 
	throws Exception {
		List<Timetable_template> timetableList = new ArrayList<>(); 
		Timetable_template timetable;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from Timetable_template where teacher_id=?");
        st.setString(1, teacher_id);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
        	timetable = new Timetable_template();
        	timetable.setTemplate_id(rs.getString("template_id"));
        	timetable.setSubject_id(rs.getString("subject_id"));
        	timetable.setTimetable_hour(rs.getString("timetable_hour"));
        	timetable.setTeacher_id(rs.getString("teacher_id"));
        	timetable.setTemplate_name(rs.getString("template_name"));
        	timetableList.add(timetable); 
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return timetableList;
	}
	//時間割テンプレートの一覧の取得(重複削除+名前検索)
	public List<Timetable_template> distinct_timetable_template_name(String template_name) 
	throws Exception {
		List<Timetable_template> timetableList = new ArrayList<>(); 
		Timetable_template timetable;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select distinct template_id,template_name from Timetable_template where template_name=?");
        st.setString(1, template_name);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
        	timetable = new Timetable_template();
        	timetable.setTemplate_id(rs.getString("template_id"));
        	timetable.setTemplate_name(rs.getString("template_name"));
        	timetableList.add(timetable); 
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return timetableList;
	}
	//時間割テンプレートの一覧の取得(重複削除+教師ID検索)
	public List<Timetable_template> distinct_timetable_template(String teacher_id) 
	throws Exception {
		List<Timetable_template> timetableList = new ArrayList<>(); 
		Timetable_template timetable;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select distinct template_name from Timetable_template where teacher_id=?");
        st.setString(1, teacher_id);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
        	timetable = new Timetable_template();
        	timetable.setTemplate_name(rs.getString("template_name"));
        	timetableList.add(timetable); 
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return timetableList;
	}
	//時間割テンプレートの一覧の取得(名前検索)
	public List<Timetable_template> timetable_template_name(String template_name) 
	throws Exception {
		List<Timetable_template> timetableList = new ArrayList<>(); 
		Timetable_template timetable;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from Timetable_template where template_name=?");
        st.setString(1, template_name);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
        	timetable = new Timetable_template();
        	timetable.setTemplate_id(rs.getString("template_id"));
        	timetable.setSubject_id(rs.getString("subject_id"));
        	timetable.setTimetable_hour(rs.getString("timetable_hour"));
        	timetable.setTeacher_id(rs.getString("teacher_id"));
        	timetable.setTemplate_name(rs.getString("template_name"));
        	timetableList.add(timetable); 
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return timetableList;
	}
	//時間割テンプレート削除用
	public int timetable_template_delete(String template_name) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"delete from Timetable_template where template_name = ?");
		st.setString(1, template_name);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
	//時間割テンプレート更新用
	public int timetable_template_update(String befname,String template_name,String subject_id,String timetable_hour) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"update Timetable_template set template_name=?,subject_id=? where template_name=? and timetable_hour=?");
		st.setString(1, template_name);
		st.setString(2, subject_id);
		st.setString(3, befname);
		st.setString(4, timetable_hour);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
	//時間割テンプレート更新用(科目削除時)
	public int timetable_template_update(String subject_id) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"update Timetable_template set subject_id=? where subject_id=?");
		st.setString(1, null);
		st.setString(2, subject_id);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
}