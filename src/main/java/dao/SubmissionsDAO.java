package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import bean.Submissions;

public class SubmissionsDAO extends DAO {
	//提出物一覧の取得
	public List<Submissions> getsubmissions() 
	throws Exception {
		List<Submissions> submissionsList = new ArrayList<>(); 
		Submissions submissions;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from Submissions");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
        	submissions = new Submissions();
        	submissions.setSubmissions_id(rs.getInt("submissions_id"));
        	submissions.setName(rs.getString("name"));
        	submissions.setSave_path(rs.getString("save_path"));
        	submissions.setCreate_date(rs.getString("create_data"));
        	submissions.setClass_id(rs.getString("class_id"));
        	submissions.setSubject_id(rs.getString("subject_id"));
        	submissionsList.add(submissions); 
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return submissionsList;
	}
	
	//提出物一覧の取得(名前検索)
	public List<Submissions> distinctsubmissions(String name) 
	throws Exception {
		List<Submissions> submissionsList = new ArrayList<>(); 
		Submissions submissions;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from Submissions where name=?");
        st.setString(1, name);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
        	submissions = new Submissions();
        	submissions.setSubmissions_id(rs.getInt("submissions_id"));
        	submissions.setName(rs.getString("name"));
        	submissions.setSave_path(rs.getString("save_path"));
        	submissions.setCreate_date(rs.getString("create_data"));
        	submissions.setClass_id(rs.getString("class_id"));
        	submissions.setSubject_id(rs.getString("subject_id"));
        	submissionsList.add(submissions); 
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return submissionsList;
	}
	//提出物一覧の取得(id検索)
	public List<Submissions> distinctsubmissions(int submissions_id) 
	throws Exception {
		List<Submissions> submissionsList = new ArrayList<>(); 
		Submissions submissions;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from Submissions where submissions_id=?");
        st.setInt(1, submissions_id);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
        	submissions = new Submissions();
        	submissions.setSubmissions_id(rs.getInt("submissions_id"));
        	submissions.setName(rs.getString("name"));
        	submissions.setSave_path(rs.getString("save_path"));
        	submissions.setCreate_date(rs.getString("create_data"));
        	submissions.setClass_id(rs.getString("class_id"));
        	submissions.setSubject_id(rs.getString("subject_id"));
        	submissionsList.add(submissions); 
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return submissionsList;
	}
	//提出物一覧の取得(名前検索+ID)
	public List<Submissions> distinctsubmissions(String name,int submissions_id) 
	throws Exception {
		List<Submissions> submissionsList = new ArrayList<>(); 
		Submissions submissions;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from Submissions where name=? and submissions_id = ?");
        st.setString(1, name);
        st.setInt(2, submissions_id);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
        	submissions = new Submissions();
        	submissions.setSubmissions_id(rs.getInt("submissions_id"));
        	submissions.setName(rs.getString("name"));
        	submissions.setSave_path(rs.getString("save_path"));
        	submissions.setCreate_date(rs.getString("create_data"));
        	submissions.setClass_id(rs.getString("class_id"));
        	submissions.setSubject_id(rs.getString("subject_id"));
        	submissionsList.add(submissions); 
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return submissionsList;
	}
	//提出物一覧の取得(クラス検索)
	public List<Submissions> distinctsubmissions_class(String class_id) 
	throws Exception {
		List<Submissions> submissionsList = new ArrayList<>(); 
		Submissions submissions;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from Submissions where class_id=?");
        st.setString(1, class_id);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
        	submissions = new Submissions();
        	submissions.setSubmissions_id(rs.getInt("submissions_id"));
        	submissions.setName(rs.getString("name"));
        	submissions.setSave_path(rs.getString("save_path"));
        	submissions.setCreate_date(rs.getString("create_data"));
        	submissions.setClass_id(rs.getString("class_id"));
        	submissions.setSubject_id(rs.getString("subject_id"));
        	submissionsList.add(submissions); 
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return submissionsList;
	}
	//提出状況の確認
	public List<String> distinctsubmissions_class_num(List<Submissions> sub_list) 
	throws Exception {
		List<String> submissionsList = new ArrayList<>();

        Connection con = getConnection();
        for(Submissions i : sub_list) {
        	int all_count=0;
        	int send_count=0;
	        PreparedStatement st = con.prepareStatement("select submissions_my_name from Submissions_alignment where submissions_id=?");
	        st.setInt(1, i.getSubmissions_id());
	        ResultSet rs = st.executeQuery();
	
	        while (rs.next()) {
	        	all_count++;
	        	String str=rs.getString("submissions_my_name");
	        	if(str != null) {
	        		send_count++;
	        	}
	        	
	        }
	        String data = send_count + "人/" + all_count + "人中";
	        submissionsList.add(data);
	        rs.close(); 
	        st.close();
        }
        con.close(); 
        
        return submissionsList;
	}
	//提出物一覧の取得(科目ID検索)
	public List<Submissions> distinctsubmissions_subject_id(String subject_id) 
	throws Exception {
		List<Submissions> submissionsList = new ArrayList<>(); 
		Submissions submissions;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from Submissions where subject_id=?");
        st.setString(1, subject_id);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
        	submissions = new Submissions();
        	submissions.setSubmissions_id(rs.getInt("submissions_id"));
        	submissions.setName(rs.getString("name"));
        	submissions.setSave_path(rs.getString("save_path"));
        	submissions.setCreate_date(rs.getString("create_data"));
        	submissions.setClass_id(rs.getString("class_id"));
        	submissions.setSubject_id(rs.getString("subject_id"));
        	submissionsList.add(submissions); 
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return submissionsList;
	}
	//提出物一覧の取得(ID検索)
	public Submissions distinctsubmissions_id(int submissions_id) 
	throws Exception {
		Submissions submissions = null;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from Submissions where submissions_id=?");
        st.setInt(1, submissions_id);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
        	submissions = new Submissions();
        	submissions.setSubmissions_id(rs.getInt("submissions_id"));
        	submissions.setName(rs.getString("name"));
        	submissions.setSave_path(rs.getString("save_path"));
        	submissions.setCreate_date(rs.getString("create_data"));
        	submissions.setClass_id(rs.getString("class_id"));
        	submissions.setSubject_id(rs.getString("subject_id"));
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return submissions;
	}
	//提出物登録
	public int submissions_registration(String name,String save_path,String create_data,String class_id,String subject_id) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"insert into Submissions(name,save_path,create_data,class_id,subject_id) values(?, ?, ?, ?, ?)");
		st.setString(1, name);
		st.setString(2, save_path);
		st.setString(3, create_data);
		st.setString(4, class_id);
		st.setString(5, subject_id);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
	//提出物ID取得用
	public int id_search(String name) 
			throws Exception {
				int submissions = 0;

		        Connection con = getConnection();

		        PreparedStatement st = con.prepareStatement("select submissions_id from Submissions where name=?");
		        st.setString(1, name);
		        ResultSet rs = st.executeQuery();

		        while (rs.next()) {
		        	submissions = (rs.getInt("submissions_id"));
		        }

		        rs.close(); 
		        st.close();
		        con.close(); 
		        
		        return submissions;
			}
	//提出物学生連携登録
	public int submissions_alignment(int submissions_id, String student_id, boolean flag) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"insert into Submissions_alignment(submissions_id,student_id,submissions_flag) values(?, ?, ?)");
		st.setInt(1, submissions_id);
		st.setString(2, student_id);
		st.setBoolean(3, flag);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
	//提出物学生登録時のフラグ関連の処理
	public int submissions_alignment_flags(int submissions_id, String student_id,String name) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"update Submissions_alignment set submissions_flag=?,submissions_my_name=? where submissions_id=? and student_id=?");
		st.setBoolean(1, true);
		st.setString(2, name);
		st.setInt(3,submissions_id);
		st.setString(4, student_id);
		
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
	//提出物学生一覧の取得(ID検索)(特定の学生)
	public Submissions submissions_alignment_list(int submissions_id,String student_id) 
	throws Exception {
		Submissions submissions = null;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from Submissions_alignment where submissions_id=? and student_id=?");
        st.setInt(1, submissions_id);
        st.setString(2, student_id);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
        	submissions = new Submissions();
        	submissions.setSubmissions_id(rs.getInt("submissions_id"));
        	submissions.setSubmissions_flag(rs.getBoolean("submissions_flag"));
        	submissions.setStudent_id(rs.getString("student_id"));
        	submissions.setSubmissions_my_name(rs.getString("submissions_my_name"));
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return submissions;
	}
	//提出物学生一覧の取得(ID検索)
	public List<Submissions> submissions_alignment_alllist(int submissions_id) 
	throws Exception {
		List<Submissions> submissionsList = new ArrayList<>(); 
		Submissions submissions;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select Sa.submissions_id,Sa.submissions_flag,Sa.student_id,Sa.submissions_my_name,Sat.name from Submissions_alignment AS Sa INNER JOIN Student_account AS Sat ON Sa.student_id = Sat.student_id where submissions_id=?");
        st.setInt(1, submissions_id);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
        	submissions = new Submissions();
        	submissions.setSubmissions_id(rs.getInt("Sa.submissions_id"));
        	submissions.setSubmissions_flag(rs.getBoolean("Sa.submissions_flag"));
        	submissions.setStudent_id(rs.getString("Sa.student_id"));
        	submissions.setSubmissions_my_name(rs.getString("Sa.submissions_my_name"));
        	submissions.setName(rs.getString("Sat.name"));
        	submissionsList.add(submissions);
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return submissionsList;
	}
	//自己管理画面における提出物の取得
	public List<Submissions> submissions_my_management(String student_id) 
	throws Exception {
		List<Submissions> submissionsList = new ArrayList<>(); 
		Submissions submissions;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("SELECT T1.submissions_id,T1.student_id,T1.submissions_flag,T2.name,T2.save_path,T2.create_data,T2.subject_id,T1.submissions_my_name FROM Submissions_alignment AS T1 JOIN Submissions AS T2 ON T1.SUBMISSIONS_ID = T2.SUBMISSIONS_ID WHERE student_id=? ORDER BY STR_TO_DATE(T2.create_data, '%Y年%m月%d日') ASC");
        st.setString(1, student_id);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
        	submissions = new Submissions();
        	submissions.setSubmissions_id(rs.getInt("submissions_id"));
        	submissions.setName(rs.getString("name"));
        	submissions.setSave_path(rs.getString("save_path"));
        	submissions.setCreate_date(rs.getString("create_data"));
        	submissions.setSubject_id(rs.getString("subject_id"));
        	submissions.setStudent_id(rs.getString("student_id"));
        	submissions.setSubmissions_flag(rs.getBoolean("submissions_flag"));
        	String submissions_date = rs.getString("create_data");
        	String submissions_my_name = rs.getString("submissions_my_name");
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        	LocalDate startdate = LocalDate.now();
        	LocalDate enddate = LocalDate.parse(submissions_date, formatter);
        	long daysBetween = ChronoUnit.DAYS.between(startdate, enddate);
        	if(daysBetween > 3) {
        		submissions.setSubmissions_date_color("#98fb98");
        	}
        	else if(daysBetween > 0) {
        		submissions.setSubmissions_date_color("#ffff00");
        	}
        	else if(daysBetween <= 0) {
        		submissions.setSubmissions_date_color("#ff0000");
        	}
        	submissionsList.add(submissions); 
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return submissionsList;
	}
	//提出物情報更新機能
	public int change_submissions(int submissions_id,String name,String save_path,String create_data,String subject_id) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"update Submissions set name = ?,save_path = ?,create_data=?,subject_id=? where submissions_id = ?");
		st.setString(1, name);
		st.setString(2, save_path);
		st.setString(3, create_data);
		st.setString(4, subject_id);
		st.setInt(5, submissions_id);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
	
	//提出物情報削除機能
	public int delete_submission(int submissions_id) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"delete from Submissions where submissions_id = ?");
		st.setInt(1, submissions_id);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
	
	//カレンダー機能での提出物
	public List<Submissions> submissions_cal(String student_id) 
	throws Exception {
		List<Submissions> submissionsList = new ArrayList<>(); 
		Submissions submissions;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("SELECT T1.submissions_id,T1.student_id,T1.submissions_flag,T2.name,T2.save_path,T2.create_data,T2.subject_id,T1.submissions_my_name FROM Submissions_alignment AS T1 JOIN Submissions AS T2 ON T1.SUBMISSIONS_ID = T2.SUBMISSIONS_ID WHERE student_id=? ORDER BY STR_TO_DATE(T2.create_data, '%Y年%m月%d日') ASC");
        st.setString(1, student_id);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
        	submissions = new Submissions();
        	submissions.setSubmissions_id(rs.getInt("submissions_id"));
        	submissions.setName(rs.getString("name"));
        	submissions.setSave_path(rs.getString("save_path"));
        	submissions.setCreate_date(rs.getString("create_data"));
        	submissions.setSubject_id(rs.getString("subject_id"));
        	submissions.setStudent_id(rs.getString("student_id"));
        	submissions.setSubmissions_flag(rs.getBoolean("submissions_flag"));
        	String submissions_date = rs.getString("create_data");
        	String submissions_my_name = rs.getString("submissions_my_name");
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        	LocalDate startdate = LocalDate.now();
        	LocalDate enddate = LocalDate.parse(submissions_date, formatter);
        	long daysBetween = ChronoUnit.DAYS.between(startdate, enddate);
        	if(submissions_my_name == null) {
        		if(daysBetween > 3) {
            		submissions.setSubmissions_date_color("#1f9c00");
            	}
            	else if(daysBetween > 0) {
            		submissions.setSubmissions_date_color("#cfa33e");
            	}
            	else if(daysBetween <= 0) {
            		submissions.setSubmissions_date_color("#ff0000");
            	}
        	}else {
        		submissions.setSubmissions_date_color("#1f9c00");
        	}
        	
        	submissionsList.add(submissions); 
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return submissionsList;
	}
}