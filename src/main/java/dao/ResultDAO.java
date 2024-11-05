package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Result;


public class ResultDAO extends DAO {
	
	//学期成績登録機能
	public int semester_result_registration(String student_id,String class_id,String semester,int result,String evaluation,String comment,String teacher_id,String grade) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"insert into Semester_result(student_id,class_id,semester,result,evaluation,comment,teacher_id,grade) values(?, ?, ?, ?, ?, ?, ?, ?)");
		st.setString(1, student_id);
		st.setString(2, class_id);
		st.setString(3, semester);
		st.setInt(4, result);
		st.setString(5, evaluation);
		st.setString(6, comment);
		st.setString(7, teacher_id);
		st.setString(8, grade);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
	//学期成績更新機能
	public int semester_result_update(String student_id,String semester,int result,String evaluation,String comment,String grade) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"update Semester_result set result=?,evaluation=?,comment=? where student_id=? and semester=? and grade=?");
		st.setString(4, student_id);
		st.setString(5, semester);
		st.setInt(1, result);
		st.setString(2, evaluation);
		st.setString(3, comment);
		st.setString(6, grade);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
	//学期成績参照機能
	public List<Result> getSemester_result(String student_id,String semester,String grade) 
	throws Exception {
		
		List<Result> result_list = new ArrayList<>(); 
		Result result;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from Semester_result where student_id=? and semester=? and grade=?");
        st.setString(1, student_id);
        st.setString(2, semester);
        st.setString(3, grade);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
        	result = new Result();
        	result.setGrade_id(rs.getString("grade_id"));
            result.setClass_id(rs.getString("class_id"));
            result.setStudent_id(rs.getString("student_id"));
            result.setSemester(rs.getString("semester"));
            result.setResult(rs.getInt("result"));
            result.setEvaluation(rs.getString("evaluation"));
            result.setComment(rs.getString("comment"));
            result.setTeacher_id(rs.getString("teacher_id"));
            result.setTeacher_id(rs.getString("grade"));
            result_list.add(result);
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return result_list;
	}
	//科目成績参照機能
	public List<Result> getSubject_result(String student_id,String subject_id) 
	throws Exception {
		
		List<Result> result_list = new ArrayList<>(); 
		Result result;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from Subject_result where student_id=? and subject_id=?");
        st.setString(1, student_id);
        st.setString(2, subject_id);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
        	result = new Result();
        	result.setGrade_id(rs.getString("grade_id"));
            result.setClass_id(rs.getString("class_id"));
            result.setStudent_id(rs.getString("student_id"));
            result.setSubject_id(rs.getString("subject_id"));
            result.setResult(rs.getInt("result"));
            result.setEvaluation(rs.getString("evaluation"));
            result.setComment(rs.getString("comment"));
            result.setTeacher_id(rs.getString("teacher_id"));
            result_list.add(result);
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return result_list;
	}
	//科目成績登録機能
	public int subject_result_registration(String student_id,String class_id,String subject_id,int result,String evaluation,String comment,String teacher_id) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"insert into Subject_result(student_id,class_id,subject_id,result,evaluation,comment,teacher_id) values(?, ?, ?, ?, ?, ?, ?)");
		st.setString(1, student_id);
		st.setString(2, class_id);
		st.setString(3, subject_id);
		st.setInt(4, result);
		st.setString(5, evaluation);
		st.setString(6, comment);
		st.setString(7, teacher_id);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
	//科目成績更新機能
	public int subject_result_update(String student_id,String subject_id,int result,String evaluation,String comment) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"update Subject_result set result=?,evaluation=?,comment=? where student_id=? and subject_id=?");
		st.setString(4, student_id);
		st.setString(5, subject_id);
		st.setInt(1, result);
		st.setString(2, evaluation);
		st.setString(3, comment);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
	//提出物成績参照機能
	public List<Result> getSubmissions_result(String student_id,String submissions_id) 
	throws Exception {
		
		List<Result> result_list = new ArrayList<>(); 
		Result result;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from Submissions_result where student_id=? and submissions_id=?");
        st.setString(1, student_id);
        st.setString(2, submissions_id);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
        	result = new Result();
        	result.setGrade_id(rs.getString("grade_id"));
            result.setClass_id(rs.getString("class_id"));
            result.setStudent_id(rs.getString("student_id"));
            result.setSubject_id(rs.getString("subject_id"));
            result.setResult(rs.getInt("result"));
            result.setEvaluation(rs.getString("evaluation"));
            result.setComment(rs.getString("comment"));
            result.setTeacher_id(rs.getString("teacher_id"));
            result_list.add(result);
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return result_list;
	}
	//提出物成績登録機能
	public int submissions_result_registration(String student_id,String class_id,String submissions_id,int result,String evaluation,String comment,String teacher_id) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"insert into Submissions_result(student_id,class_id,submissions_id,result,evaluation,comment,teacher_id) values(?, ?, ?, ?, ?, ?, ?)");
		st.setString(1, student_id);
		st.setString(2, class_id);
		st.setString(3, submissions_id);
		st.setInt(4, result);
		st.setString(5, evaluation);
		st.setString(6, comment);
		st.setString(7, teacher_id);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
	//提出物成績更新機能
	public int submissions_result_update(String student_id,String submissions_id,int result,String evaluation,String comment) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"update Submissions_result set result=?,evaluation=?,comment=? where student_id=? and submissions_id=?");
		st.setString(4, student_id);
		st.setString(5, submissions_id);
		st.setInt(1, result);
		st.setString(2, evaluation);
		st.setString(3, comment);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
}