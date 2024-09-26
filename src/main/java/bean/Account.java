package bean;

public class Account implements java.io.Serializable {
	
	private String student_id;
	private String name;
	private String grade;
	private String class_id;
	private String password;
	private String address;
	private String nickname;
	private String icon;
	
	public String getStudent_id() {
		return student_id;
	}
	public String getName() {
		return name;
	}
	public String getGrade() {
		return grade;
	}
	public String getClass_id() {
		return class_id;
	}
	public String getPassword() {
		return password;
	}
	public String getAddress() {
		return address;
	}
	public String getNickname() {
		return nickname;
	}
	public String getIcon() {
		return icon;
	}
	
	public void setStudent_id(String student_id) {
		this.student_id=student_id;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setGrade(String grade) {
		this.grade=grade;
	}
	public void setClass_id(String class_id) {
		this.class_id=class_id;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	public void setAddress(String address) {
		this.address=address;
	}
	public void setNickname(String nickname) {
		this.nickname=nickname;
	}
	public void setIcon(String icon) {
		this.icon=icon;
	}
}