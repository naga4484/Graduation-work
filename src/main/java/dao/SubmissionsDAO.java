package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        	submissions.setName("name");
        	submissions.setSave_path("save_path");
        	submissions.setCreate_date("create_data");
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
        	submissions.setName("name");
        	submissions.setSave_path("save_path");
        	submissions.setCreate_date("create_data");
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
        	submissions.setName("name");
        	submissions.setSave_path("save_path");
        	submissions.setCreate_date("create_data");
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
	//提出物一覧の取得(ID検索)
	public Submissions distinctsubmissions_id(int submissions_id) 
	throws Exception {
		Submissions submissions = new Submissions();

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from Submissions where submissions_id=?");
        st.setInt(1, submissions_id);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
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
	public int submissions_alignment(int submissions_id, String student_id) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"insert into Submissions_alignment values(?, ?)");
		st.setInt(1, submissions_id);
		st.setString(2, student_id);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
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
}