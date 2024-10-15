package bean;

public class Teacheraccount implements java.io.Serializable {
	
	private String teacher_id;
	private String name;
	private String class_id;
	private String password;
	private String address;
	private String nickname;
	private String icon;
	private String account_kind;
	
	public String getTeacher_id() {
		return teacher_id;
	}
	public String getName() {
		return name;
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
	public String getAccount_kind() {
		return account_kind;
	}
	
	public void setTeacher_id(String teacher_id) {
		this.teacher_id=teacher_id;
	}
	public void setName(String name) {
		this.name=name;
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
	public void setAccount_kind(String account_kind) {
		this.account_kind=account_kind;
	}
}