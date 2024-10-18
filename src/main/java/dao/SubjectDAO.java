package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;

public class SubjectDAO extends DAO {
	
	
	//科目一覧の取得
	public List<Subject> getallsubject() 
	throws Exception {
		List<Subject> subjectList = new ArrayList<>(); 
		Subject subject;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from Subject");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
        	subject = new Subject();
        	subject.setSubject_id(rs.getString("subject_id"));
        	subject.setSubject_name(rs.getString("subject_name"));
        	subject.setTotal_unit(rs.getInt("total_unit"));
        	subject.setClass_id(rs.getString("class_id"));
        	subjectList.add(subject); 
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return subjectList;
	}
	
	//科目ID検索機能
	public List<Subject> subject_search_no(String subject_id)
	throws Exception {
		List<Subject> subject_no_list = new ArrayList<>(); 

		Connection con=getConnection();
		
		PreparedStatement st;
		st=con.prepareStatement(
				"select * from Subject where subject_id=?");
		st.setString(1, subject_id);
		ResultSet rs=st.executeQuery();
		
		while (rs.next()) {
			Subject subject=new Subject();
			subject.setSubject_id(rs.getString("subject_id"));
			subject.setClass_id(rs.getString("class_id"));
			subject.setSubject_name(rs.getString("subject_name"));
			subject.setTotal_unit(rs.getInt("total_unit"));
			subject_no_list.add(subject);
		}
		
		st.close();
		con.close();
		return subject_no_list;
	}
	
	//科目登録機能
	public int subject_registration(String subject_id,String class_id,String subject_name,int total_unit) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"insert into Subject values(?, ?, ?, ?)");
		st.setString(1, subject_id);
		st.setString(2, class_id);
		st.setString(3, subject_name);
		st.setInt(4, total_unit);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
	
	//科目削除機能
	public int delete_subject(String subject_id) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"delete from Subject where subject_id = ?");
		st.setString(1, subject_id);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
	//科目情報更新機能
	public int change_subject(String subject_id,String subject_name,int total_unit) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"update Subject set subject_name = ?,total_unit = ? where subject_id = ?");
		st.setString(1, subject_name);
		st.setInt(2, total_unit);
		st.setString(3, subject_id);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
}

    