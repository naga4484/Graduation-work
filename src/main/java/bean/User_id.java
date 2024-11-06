package bean;

public class User_id implements java.io.Serializable {

	private int user_id;
	private String student_id;
	private String teacher_id;
	

	public int getUser_id() {
		return user_id;
	}
	public String getStudent_id() {
		return student_id;
	}
	public String getTeacher_id() {
		return teacher_id;
	}

	public void setUser_id(int user_id) {
		this.user_id=user_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id=student_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id=teacher_id;
	}
}