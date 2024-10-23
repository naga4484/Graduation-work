package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Submissions;

public class SubmissionsDAO extends DAO {
	
	
	//提出物一覧の取得
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
        	submissions.setSubmissions_id(rs.getString("submissions_id"));
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
}